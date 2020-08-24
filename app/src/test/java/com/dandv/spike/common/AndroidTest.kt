package com.dandv.spike.common

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.dandv.spike.BuildConfig
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

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

    class ApplicationStub : Application(), KoinComponent {

        override fun onCreate() {
            super.onCreate()
            initTestComponent()
        }

        private fun initTestComponent() {
            startKoin {
                androidContext(this@ApplicationStub)
                modules(emptyList())
            }
        }
    }
}
