package com.dandv.spike.di

import android.app.Application
import com.dandv.spike.common.AndroidTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TestAppModule::class,
        ActivityBuilderModule::class]
)
interface TestAppComponent : AppBaseComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        abstract fun application(application: Application): Builder

        abstract fun build(): TestAppComponent
    }

    fun inject(appApplication: AndroidTest.ApplicationStub)

}
