package com.dandv.spike.di.module.base

import com.dandv.data.di.ClientModule
import com.dandv.data.di.RepositoryModule
import com.dandv.spike.di.module.ui.ImageLoaderModule
import dagger.Module

@Module(
    includes = [
        ImageLoaderModule::class,
        RepositoryModule::class,
        ClientModule::class,
        ViewModelModule::class]
)
class AppModule