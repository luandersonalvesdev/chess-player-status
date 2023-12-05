package br.com.buscadorchesscom.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.buscadorchesscom.R
import br.com.buscadorchesscom.databinding.ActivityMainBinding
import br.com.buscadorchesscom.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()
        binding.mainTiSearchPlayer.setEndIconOnClickListener {
            val username = binding.mainTiSearchPlayer.editText?.text.toString().trim()
            viewModel.getPlayerProfile(username)
            val intent = Intent(baseContext, PlayerStatsActivity::class.java)
            startActivity(intent)
        }
    }

    /*override fun onClick(v: View?) {
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
    }*/
}