package br.com.buscadorchesscom

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.buscadorchesscom.api.ChessPlayerStatsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class PlayerStatsActivity : AppCompatActivity() {

    private val chessPlayerStatsService = ChessPlayerStatsService.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_status)
    }

    override fun onStart() {
        super.onStart()

        val playerUsername = intent.getStringExtra("playerUsername").toString()

        Log.i("ChessDebug", playerUsername)

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val responsePlayerStats = chessPlayerStatsService
                    .getPlayerStats(playerUsername)

                val playerStats = responsePlayerStats.body()!!

                withContext(Dispatchers.Main) {
                }

            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(baseContext, "Erro de conexão", Toast.LENGTH_LONG)
                        .show()
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        baseContext,
                        "Site do chess.com está fora de ar",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        baseContext,
                        "Algum erro desconhecido aconteceu, tente novamente",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}