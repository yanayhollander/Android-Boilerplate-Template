package com.hollander.template.data.repository

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson
import com.hollander.template.R
import com.hollander.template.data.api.DotaApi
import com.hollander.template.data.dto.Hero
import com.hollander.template.domain.repository.DotaRepository
import javax.inject.Inject


class DotaRepositoryImpl @Inject constructor(
    private val dotaApi: DotaApi,
    private val appContext: Application,
    private val sharedPreferences: SharedPreferences
) : DotaRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the repository, The app name is $appName")
    }

    override suspend fun getHeroes(): List<Hero> {
        val heroes = sharedPreferences.getString("heroesList", null)

        if (heroes != null) {
            return Gson().fromJson(heroes, Array<Hero>::class.java).toList()
        }

        return dotaApi.getHeroes().apply {
            sharedPreferences.edit().putString("heroesList", Gson().toJson(this)).apply()
        }
    }
}