package com.tapok.dalimopromotions.di

import android.app.Application
import android.content.Context
import com.tapok.dalimopromotions.database.PromotionDao
import com.tapok.dalimopromotions.database.PromotionDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PromotionModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): PromotionDao {
        return PromotionDatabase.start(context)
    }

    @Provides
    fun context(): Context {
        return application
    }
}