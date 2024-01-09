package com.hollander.template.data.repository

import android.app.Application
import com.google.gson.Gson
import com.hollander.template.data.api.DotaApi
import com.hollander.template.data.dto.Hero
import com.hollander.template.domain.repository.DotaRepository
import com.hollander.template.ui.sharedPreferences.sharedPreferences
import javax.inject.Inject


class DotaRepositoryImpl @Inject constructor(
    private val dotaApi: DotaApi,
    appContext: Application,
    private val gson: Gson
) : DotaRepository {

    private var heroes by appContext.sharedPreferences("heroesList")

    override suspend fun getHeroes(): List<Hero> {
        if (heroes != null) {
            return gson.fromJson(heroes, Array<Hero>::class.java).toList()
        }

        return dotaApi.getHeroes().apply {
            heroes = gson.toJson(this)
        }
    }
}