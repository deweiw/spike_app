package com.dandv.spike.di.module.base

import com.dandv.data.di.ClientModule
import com.dandv.data.di.RepositoryModule
import com.dandv.spike.di.module.ui.ToolsModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        ToolsModule::class,
        RepositoryModule::class,
        ClientModule::class,
        ViewModelModule::class]
)
class AppModule
