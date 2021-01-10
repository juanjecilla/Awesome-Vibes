package com.scallop.data.repository

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.scallop.data.BaseTest
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.mappers.MusicEntityDataMapper
import com.scallop.data.utils.TestEntityUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
internal class MusicLocalImplTest : BaseTest() {

    private lateinit var mLocalDataSource: MusicLocalImpl

    private val mFakeSongs = TestEntityUtils.getRandomSongs(10)

    @Before
    override fun setup() {
        super.setup()
        mLocalDataSource = MusicLocalImpl(mDao, mDataEntityMapper = MusicDataEntityMapper(), mEntityDataMapper = MusicEntityDataMapper())
    }

    @Test
    fun `when all favorites are requested , then return all saved favorites`() =
        runBlocking(Dispatchers.IO) {
            for (song in mFakeSongs){
                mLocalDataSource.saveSong(song)
            }

            // When we get all favorite items
            val songs = mLocalDataSource.getSongsFromAlbum(1)
            songs.collect {
                val actual = it.sortedBy { it1 -> it1.trackId }
                val expected = mFakeSongs.sortedBy { it1 -> it1.trackId }
                Truth.assertThat(actual).isEqualTo(expected)
            }
        }
}