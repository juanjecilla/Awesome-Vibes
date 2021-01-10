package com.scallop.awesomevibes.utils

import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
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

        fun getRandomAlbums(size: Long): List<AlbumEntity> {
            val list = mutableListOf<AlbumEntity>()

            for (i in 0..size) {
                list.add(getRandomAlbum())
            }

            return list
        }

        private fun getRandomAlbum() = AlbumEntity(
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toLong(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toDouble(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000).toString(),
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000).toString(),
        )
    }
}