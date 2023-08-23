package com.example.composeapplication.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.composeapplication.data.local.model.BeerEntity

@Dao
interface BeerDao {

    @Upsert
    suspend fun upsertAll(beers: List<BeerEntity>)

    @Query("SELECT * FROM beer")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM beer")
    suspend fun clearAll()
}