package com.tapok.dalimopromotions.di

import android.app.Application

class PromotionApplication: Application() {
    lateinit var databaseComponent: DatabaseComponent
    override fun onCreate() {
        super.onCreate()
        databaseComponent = DaggerDatabaseComponent.builder().promotionModule(PromotionModule(this)).build()
    }
}