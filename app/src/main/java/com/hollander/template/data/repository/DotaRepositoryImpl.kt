package com.hollander.template.data.repository

import android.app.Application
import com.google.gson.Gson
import com.hollander.template.R
import com.hollander.template.data.api.DotaApi
import com.hollander.template.data.dto.Hero
import com.hollander.template.domain.repository.DotaRepository
import com.hollander.template.ui.sharedPreferences.sharedPreferences
import javax.inject.Inject


class DotaRepositoryImpl @Inject constructor(
    private val dotaApi: DotaApi,
    appContext: Application,
) : DotaRepository {
    private var heroes by appContext.sharedPreferences("heroesList")

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the repository, The app name is $appName")
    }

    override suspend fun getHeroes(): List<Hero> {
        if (heroes != null) {
            return Gson().fromJson(heroes, Array<Hero>::class.java).toList()
        }

        return dotaApi.getHeroes().apply {
            heroes = Gson().toJson(this)
        }
    }
}