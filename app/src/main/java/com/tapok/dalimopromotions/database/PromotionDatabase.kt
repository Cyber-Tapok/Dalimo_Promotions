package com.tapok.dalimopromotions.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tapok.dalimopromotions.model.Promotion

@Database(entities = [Promotion::class], version = 3, exportSchema = false)
abstract class PromotionDatabase : RoomDatabase() {
    abstract fun promotionDao(): PromotionDao

    companion object {
        fun start(context: Context): PromotionDao =
            Room.databaseBuilder(context, PromotionDatabase::class.java, "PromotionDB")
                .fallbackToDestructiveMigration().build().promotionDao()
    }
}