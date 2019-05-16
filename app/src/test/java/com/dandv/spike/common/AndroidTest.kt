package com.dandv.spike.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.dandv.spike.BuildConfig
import com.dandv.spike.di.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(
    constants = BuildConfig::class,
    application = AndroidTest.ApplicationStub::class,
    sdk = [21]
)
abstract class AndroidTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks = InjectMocksRule.create(this@AndroidTest)

    fun context(): Context = RuntimeEnvironment.application

    fun activityContext(): Context = mock(AppCompatActivity::class.java)

    class ApplicationStub : Application(), HasActivityInjector {

        @Inject
        lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

        override fun onCreate() {
            super.onCreate()
            initTestComponent()
        }

        private fun initTestComponent() {
            return DaggerTestAppComponent.builder()
                .application(this)
                .build().inject(this)
        }

        override fun activityInjector(): AndroidInjector<Activity> {
            return activityDispatchingAndroidInjector
        }
    }
}