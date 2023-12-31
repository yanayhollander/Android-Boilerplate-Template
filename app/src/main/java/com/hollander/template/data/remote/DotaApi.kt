package com.hollander.template.data.remote

import com.hollander.template.data.remote.repository.Hero
import retrofit2.http.GET

interface DotaApi {
    @GET("heroes")
    suspend fun getHeroes(): List<Hero>
}