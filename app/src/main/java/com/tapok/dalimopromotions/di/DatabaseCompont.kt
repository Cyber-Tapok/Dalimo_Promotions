package com.tapok.dalimopromotions.di

import com.tapok.dalimopromotions.MasterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PromotionModule::class])
interface DatabaseComponent {
    fun inject(masterFragment: MasterFragment)
}