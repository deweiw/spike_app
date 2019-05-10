package com.dandv.spike.di.module.base

import com.dandv.data.di.ClientModule
import com.dandv.data.di.RepositoryModule
import com.dandv.spike.di.module.ui.ToolsModule
import dagger.Module

@Module(
    includes = [
        ToolsModule::class,
        RepositoryModule::class,
        ClientModule::class,
        ViewModelModule::class]
)
class AppModule