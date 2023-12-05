package br.com.buscadorchesscom.data.repository

import br.com.buscadorchesscom.data.network.ChessPlayerProfileDataSource
import br.com.buscadorchesscom.model.ChessPlayerProfileData

class ChessPlayerProfileRepository {

    private val chessPlayerProfileDataSource = ChessPlayerProfileDataSource()

    suspend fun getPlayerProfile(username: String): ChessPlayerProfileData? {
        val chessPlayerProfile = chessPlayerProfileDataSource.getChessPlayerProfile(username)

        if (chessPlayerProfile != null) {
            return ChessPlayerProfileData(
                avatar = chessPlayerProfile.avatar,
                name = chessPlayerProfile.name,
                joined = chessPlayerProfile.joined,
                status = chessPlayerProfile.status,
                title = chessPlayerProfile.title,
                username = chessPlayerProfile.username,
                followers = chessPlayerProfile.followers,
                lastOnline = chessPlayerProfile.lastOnline,
            )
        }
        return null
    }

}