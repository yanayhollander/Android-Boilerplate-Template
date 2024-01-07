package com.hollander.template.data.dto

import com.google.gson.annotations.SerializedName

const val IMAGE_URL = "https://cdn.dota2.com/apps/dota2/images/heroes/"

data class Hero(
    val id: Int,
    val name: String,
    @SerializedName("localized_name")
    val localizedName: String,
    @SerializedName("primary_attr")
    val primaryAttr: String,
    @SerializedName("attack_type")
    val attackType: String,
    val roles: List<String>,
) {
    val photoUrl: String
        get() {
            val heroName = name.replace("npc_dota_hero_", "")
            return "$IMAGE_URL${heroName}_full.png"
        }
}

