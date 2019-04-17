package com.boni.neon.dagger

import com.boni.neon.dagger.modules.AppModule
import com.boni.neon.dagger.home.HomeModule
import com.boni.neon.dagger.home.HomeSubComponent
import com.boni.neon.dagger.modules.DataModule
import com.boni.neon.dagger.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {
    fun plus(homeModule: HomeModule): HomeSubComponent
}