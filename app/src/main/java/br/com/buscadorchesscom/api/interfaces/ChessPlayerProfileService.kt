package br.com.buscadorchesscom.api.interfaces

import br.com.buscadorchesscom.model.ChessPlayerProfileData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChessPlayerProfileService {

    @GET("player/{username}")
    suspend fun getPlayerProfile(
        @Path("username") username: String
    ): Response<ChessPlayerProfileData>

}