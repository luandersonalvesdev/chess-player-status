package br.com.buscadorchesscom.data.network

import br.com.buscadorchesscom.data.api.ChessPlayerProfileService
import br.com.buscadorchesscom.model.ChessPlayerProfileData

class ChessPlayerProfileDataSource {

    private val chessPlayerProfileService = ChessPlayerProfileService.instance

    suspend fun getChessPlayerProfile(username: String): ChessPlayerProfileData? {
        val responsePlayerProfile = chessPlayerProfileService.getPlayerProfile(username)
        return responsePlayerProfile.body()
    }

}