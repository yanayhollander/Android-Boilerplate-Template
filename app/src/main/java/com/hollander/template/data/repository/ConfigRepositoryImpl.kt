package com.hollander.template.data.repository

import com.hollander.template.BuildConfig
import com.hollander.template.domain.repository.ConfigRepository
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor() : ConfigRepository {
    override fun isDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }
}