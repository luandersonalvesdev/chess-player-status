package br.com.buscadorchesscom

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.buscadorchesscom.data.api.ChessPlayerProfileService
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mTextInputSearch: TextInputLayout by lazy { findViewById(R.id.main_ti_search) }
    private val mBtnSearch: Button by lazy { findViewById(R.id.main_btn_search) }
    private val chessPlayerProfileService = ChessPlayerProfileService.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        mBtnSearch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.main_btn_search -> {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val playerUsername = mTextInputSearch.editText?.text.toString().trim()

                        val responsePlayerProfile = chessPlayerProfileService
                            .getPlayerProfile(playerUsername)

                        val playerProfile = responsePlayerProfile.body()

                        if (playerProfile?.username != null) {
                            val intent = Intent(baseContext, PlayerStatsActivity::class.java)
                            intent.putExtra("playerAvatar", playerProfile.avatar)
                            intent.putExtra("playerName", playerProfile.name)
                            intent.putExtra("playerJoined", playerProfile.joined)
                            intent.putExtra("playerStatus", playerProfile.status)
                            intent.putExtra("playerTitle", playerProfile.title)
                            intent.putExtra("playerLastOnline", playerProfile.lastOnline)
                            intent.putExtra("playerFollowers", playerProfile.followers)
                            intent.putExtra("playerUsername", playerProfile.username)

                            startActivity(intent)
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    baseContext,
                                    "Usuário não encontrado",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
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
    }
}