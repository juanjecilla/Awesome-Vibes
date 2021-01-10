package com.scallop.data.utils

import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.SongEntity
import kotlin.random.Random

/**
 * Created by Yossi Segev on 09/02/2018.
 */
class TestEntityUtils {

    companion object {
        fun getTestArtistResponse(size: Long): ItunesApiResponseEntity<ArtistEntity> =
            ItunesApiResponseEntity(
                size,
                getRandomArtists(size)
            )

        fun getRandomArtists(size: Long): List<ArtistEntity> {
            val list = mutableListOf<ArtistEntity>()

            for (i in 0..size) {
                list.add(getRandomArtist())
            }

            return list
        }

        private fun getRandomArtist(): ArtistEntity = ArtistEntity(
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
        )

        fun getRandomSongs(size: Long, collectionId: Long = 1L): List<SongEntity> {
            val list = mutableListOf<SongEntity>()

            for (i in 0..size) {
                list.add(getRandomSong())
            }

            return list
        }

        private fun getRandomSong(collectionId : Long = 1L): SongEntity = SongEntity(
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            collectionId,
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toDouble(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000),
            Random.nextBoolean(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toDouble(),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString()
        )
    }
}