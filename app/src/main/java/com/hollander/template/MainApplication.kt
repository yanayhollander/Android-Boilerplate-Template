package com.hollander.template

import android.app.Application
import com.hollander.template.domain.repository.ConfigRepository
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject


@HiltAndroidApp
open class MainApplication : Application() {

    @Inject
    lateinit var configRepository: ConfigRepository

    override fun onCreate() {
        super.onCreate()

        if (configRepository.isDebugMode()) {
            Timber.plant(Timber.DebugTree())
        }
    }
}