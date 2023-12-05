package br.com.buscadorchesscom.data.api

import br.com.buscadorchesscom.data.api.interfaces.ChessPlayerProfileService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChessPlayerProfileService {

    private const val BASE_URL = "https://api.chess.com/pub/"

    val instance: ChessPlayerProfileService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ChessPlayerProfileService::class.java)
    }

}