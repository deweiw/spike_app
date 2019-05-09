package com.dandv.spike

import android.app.Activity
import android.app.Application
import com.dandv.spike.di.component.DaggerAppBaseComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AppApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }

    private fun initComponent() {
        return DaggerAppBaseComponent.builder()
            .application(this)
            .build().inject(this)

    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}