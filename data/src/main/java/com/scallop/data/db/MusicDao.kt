package com.scallop.data.db

import androidx.room.*
import com.scallop.data.entitites.MovieDetailData
import com.scallop.data.entitites.MovieItemData
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Query("Select * from movie_items")
    fun getMovieItems(): Flow<List<MovieItemData>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieItems(movieItems: List<MovieItemData>)

    @Update
    fun updateMovieItem(movieItem: MovieItemData)

    @Query("Select * from movie_items where fav = 1")
    fun getFavouriteMovieItems(): Flow<List<MovieItemData>>

    @Query("DELETE FROM movie_items")
    fun clearMovieItems()

    @Query("Select * from movie_detail Where id = :id")
    fun getMovieDetail(id: Long): Flow<MovieDetailData?>

}