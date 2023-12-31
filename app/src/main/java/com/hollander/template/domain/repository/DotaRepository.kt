package com.hollander.template.domain.repository

import com.hollander.template.data.remote.repository.Hero

interface DotaRepository {
    suspend fun getHeroes(): List<Hero>
}