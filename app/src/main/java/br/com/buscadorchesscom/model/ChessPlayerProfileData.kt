package br.com.buscadorchesscom.model

import com.google.gson.annotations.SerializedName

data class ChessPlayerProfileData (
    val avatar: String,
    val name: String,
    val joined: Long,
    val status: String,
    val title: String,
    @SerializedName("last_online") val lastOnline: Long,
)