package com.hollander.template

import android.app.Application
import androidx.annotation.CallSuper
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule


@HiltAndroidTest
abstract class BaseHiltTest {

    val application: Application = ApplicationProvider.getApplicationContext()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @CallSuper
    @Before
    open fun setup() {
        hiltRule.inject()
    }
}