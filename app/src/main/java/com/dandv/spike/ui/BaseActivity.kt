package com.dandv.spike.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun setupViewModel()

    abstract fun observeViewModelState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        setupViewModel()
        observeViewModelState()
    }
}