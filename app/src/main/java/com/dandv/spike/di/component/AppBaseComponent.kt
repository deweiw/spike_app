package com.dandv.spike.di.component

import android.app.Application
import com.dandv.spike.AppApplication
import com.dandv.spike.di.module.base.AppModule
import com.dandv.spike.di.module.ui.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class]
)
interface AppBaseComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppBaseComponent
    }

    fun inject(appApplication: AppApplication)

}