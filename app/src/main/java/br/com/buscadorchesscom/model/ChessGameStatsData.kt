package br.com.buscadorchesscom.model

data class ChessGameStatsData (
    val last: ChessLastStatsData,
    val best: ChessBestStatsData,
    val record: ChessRecordStatsData
)