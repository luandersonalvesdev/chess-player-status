package br.com.buscadorchesscom.data.api

import br.com.buscadorchesscom.data.api.interfaces.ChessPlayerStatsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChessPlayerStatsService {

    private const val BASE_URL = "https://api.chess.com/pub/"

    val instance: ChessPlayerStatsService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ChessPlayerStatsService::class.java)
    }

}