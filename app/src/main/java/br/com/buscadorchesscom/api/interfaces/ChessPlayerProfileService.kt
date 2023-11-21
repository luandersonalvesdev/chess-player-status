package br.com.buscadorchesscom.api.interfaces

import br.com.buscadorchesscom.model.ChessPlayerProfileData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChessPlayerProfileService {

    @GET("player")
    fun getPlayerProfile(
        @Path("player") player: String
    ): Call<ChessPlayerProfileData>

}