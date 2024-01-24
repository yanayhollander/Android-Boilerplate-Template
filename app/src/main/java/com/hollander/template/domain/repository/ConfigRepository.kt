package com.hollander.template.domain.repository

interface ConfigRepository {
    fun isDebugMode(): Boolean
}