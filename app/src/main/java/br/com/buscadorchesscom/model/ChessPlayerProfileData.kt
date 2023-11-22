package br.com.buscadorchesscom.model

import com.google.gson.annotations.SerializedName

data class ChessPlayerProfileData (
    val avatar: String,
    val name: String,
    val joined: Long,
    val status: String,
    val title: String,
    val username: String,
    val followers: Int,
    @SerializedName("last_online") val lastOnline: Long,
)