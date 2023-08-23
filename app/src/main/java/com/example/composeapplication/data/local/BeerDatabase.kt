package com.example.composeapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composeapplication.data.local.model.BeerEntity

@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {
    abstract val dao: BeerDao
}