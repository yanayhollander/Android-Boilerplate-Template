package com.hollander.template.data.remote.repository

import android.app.Application
import com.hollander.template.R
import com.hollander.template.data.remote.DotaApi
import com.hollander.template.domain.repository.DotaRepository
import javax.inject.Inject


class DotaRepositoryImpl @Inject constructor(
    private val dotaApi: DotaApi,
    private val appContext: Application
) : DotaRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the repository, The app name is $appName")
    }

    override suspend fun getHeroes(): List<Hero> {
        return dotaApi.getHeroes()
    }
}