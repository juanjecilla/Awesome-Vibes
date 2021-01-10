package com.scallop.data.repository

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.scallop.data.BaseTest
import com.scallop.data.entitites.AlbumData
import com.scallop.data.entitites.ArtistData
import com.scallop.data.entitites.MusicVideoData
import com.scallop.data.entitites.SongData
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.utils.getJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.lang.reflect.Type


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
internal class MusicRemoteImplTest : BaseTest() {

    private lateinit var mRemote: MusicRemoteImpl
    private val mMapper = MusicDataEntityMapper()

    @Before
    override fun setup() {
        super.setup()
        mRemote = MusicRemoteImpl(mApi)
    }

    @Test
    fun `getting artists list and expect correct results`() {
        val original = getJson("json/artists.json")
        val json = JSONObject(original).getJSONArray("results")

        val type: Type =
            Types.newParameterizedType(MutableList::class.java, ArtistData::class.java)
        val adapter: JsonAdapter<List<ArtistData>> = moshi.adapter(type)

        val expected = adapter.fromJson(json.toString())

        runBlocking {
            val results = mRemote.getArtistsByName("rolling", 0)
            results.collect {
                Truth.assertThat(it).isNotEmpty()
                Truth.assertThat(it).hasSize(20)
                expected?.let { it1 ->
                    Truth.assertThat(it).isEqualTo(mMapper.mapArtistsToEntity(it1))
                }
            }
        }
    }

    @Test
    fun `getting albums list and expect correct results`() {
        val original = getJson("json/albums.json")
        val json = JSONObject(original).getJSONArray("results")

        val type: Type =
            Types.newParameterizedType(MutableList::class.java, AlbumData::class.java)
        val adapter: JsonAdapter<List<AlbumData>> = moshi.adapter(type)

        val expected = adapter.fromJson(json.toString())

        runBlocking {
            val results = mRemote.getAlbumsFromArtist("rolling", 0)
            results.collect {
                Truth.assertThat(it).isNotEmpty()
                Truth.assertThat(it).hasSize(20)
                expected?.let { it1 ->
                    Truth.assertThat(it).isEqualTo(mMapper.mapAlbumToEntity(it1))
                }
            }
        }
    }

    @Test
    fun `getting song list and expect correct results`() {
        val original = getJson("json/songs.json")
        val json = JSONObject(original).getJSONArray("results")

        val type: Type =
            Types.newParameterizedType(MutableList::class.java, SongData::class.java)
        val adapter: JsonAdapter<List<SongData>> = moshi.adapter(type)

        val expected = adapter.fromJson(json.toString())?.filter { it.collectionId == 1440812661L }

        runBlocking {
            val results = mRemote.getSongsFromAlbum("rolling", 1440812661, 0)
            results.collect {
                Truth.assertThat(it).isNotEmpty()
                Truth.assertThat(it).hasSize(10)
                expected?.let { it1 ->
                    Truth.assertThat(it).isEqualTo(mMapper.mapSongToEntity(it1))
                }
            }
        }
    }

    @Test
    fun `getting music video and expect correct result`() {
        val original = getJson("json/video.json")
        val json = JSONObject(original).getJSONArray("results")

        val type: Type =
            Types.newParameterizedType(MutableList::class.java, MusicVideoData::class.java)
        val adapter: JsonAdapter<List<MusicVideoData>> = moshi.adapter(type)

        val expected = adapter.fromJson(json.toString())?.get(0)

        runBlocking {
            val results = mRemote.getMusicVideo("chojin", 439672238)
            results.collect {
                expected?.let { it1 ->
                    Truth.assertThat(it).isEqualTo(mMapper.mapMusicVideoToEntity(it1))
                }
            }
        }
    }

    @Test
    fun `getting music video and expecting zero results`() {
        runBlocking {
            val results = mRemote.getMusicVideo("not_found", 1234)
            results.collect {
                Truth.assertThat(it).isNull()
            }
        }
    }
}