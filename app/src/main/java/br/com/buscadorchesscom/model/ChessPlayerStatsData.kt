package br.com.buscadorchesscom.model

import com.google.gson.annotations.SerializedName

data class ChessPlayerStatsData (
    val fide: Int,
    @SerializedName("chess_rapid") val chessRapid: ChessGameStatsData,
    @SerializedName("chess_blitz") val chessBlitz: ChessGameStatsData,
    @SerializedName("chess_bullet") val chessBullet: ChessGameStatsData
)