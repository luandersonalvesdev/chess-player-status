package br.com.buscadorchesscom

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.buscadorchesscom.data.api.ChessPlayerStatsService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class PlayerStatsActivity : AppCompatActivity() {

    private val mPlayerUsername: TextView by lazy { findViewById(R.id.stats_tv_username) }
    private val mPlayerAvatar: ShapeableImageView by lazy { findViewById(R.id.stats_iv_avatar) }
    private val mRapidCurrentRating: TextView by lazy { findViewById(R.id.stats_tv_rapid_current_rating) }
    private val mRapidBestRating: TextView by lazy { findViewById(R.id.stats_tv_rapid_best_rating) }
    private val mRapidWin: TextView by lazy { findViewById(R.id.stats_tv_rapid_win) }
    private val mRapidLoss: TextView by lazy { findViewById(R.id.stats_tv_rapid_loss) }
    private val mRapidDraw: TextView by lazy { findViewById(R.id.stats_tv_rapid_draw) }
    private val mBulletCurrentRating: TextView by lazy { findViewById(R.id.stats_tv_bullet_current_rating) }
    private val mBulletBestRating: TextView by lazy { findViewById(R.id.stats_tv_bullet_best_rating) }
    private val mBulletWin: TextView by lazy { findViewById(R.id.stats_tv_bullet_win) }
    private val mBulletLoss: TextView by lazy { findViewById(R.id.stats_tv_bullet_loss) }
    private val mBulletDraw: TextView by lazy { findViewById(R.id.stats_tv_bullet_draw) }
    private val mBlitzCurrentRating: TextView by lazy { findViewById(R.id.stats_tv_blitz_current_rating) }
    private val mBlitzBestRating: TextView by lazy { findViewById(R.id.stats_tv_blitz_best_rating) }
    private val mBlitzWin: TextView by lazy { findViewById(R.id.stats_tv_blitz_win) }
    private val mBlitzLoss: TextView by lazy { findViewById(R.id.stats_tv_blitz_loss) }
    private val mBlitzDraw: TextView by lazy { findViewById(R.id.stats_tv_blitz_draw) }

    private val chessPlayerStatsService = ChessPlayerStatsService.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_stats)
    }

    override fun onStart() {
        super.onStart()

        val playerUsername = intent.getStringExtra("playerUsername").toString()
        val playerAvatar = intent.getStringExtra("playerAvatar").toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val responsePlayerStats = chessPlayerStatsService
                    .getPlayerStats(playerUsername)

                val playerStats = responsePlayerStats.body()!!

                withContext(Dispatchers.Main) {
                    mPlayerUsername.text = playerUsername

                    Glide.with(baseContext)
                        .load(playerAvatar)
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .into(mPlayerAvatar)

                    mRapidCurrentRating.text = "Rating atual: ${playerStats.chessRapid.last.rating}"
                    mRapidBestRating.text = "Melhor rating: ${playerStats.chessRapid.best.rating}"
                    mRapidWin.text = "V: ${playerStats.chessRapid.record.win}"
                    mRapidLoss.text = "D: ${playerStats.chessRapid.record.loss}"
                    mRapidDraw.text = "E: ${playerStats.chessRapid.record.draw}"

                    mBlitzCurrentRating.text = "Rating atual: ${playerStats.chessBlitz.last.rating}"
                    mBlitzBestRating.text = "Melhor rating: ${playerStats.chessBlitz.best.rating}"
                    mBlitzWin.text = "V: ${playerStats.chessBlitz.record.win}"
                    mBlitzLoss.text = "D: ${playerStats.chessBlitz.record.loss}"
                    mBlitzDraw.text = "E: ${playerStats.chessBlitz.record.draw}"

                    mBulletCurrentRating.text = "Rating atual: ${playerStats.chessBullet.last.rating}"
                    mBulletBestRating.text = "Melhor rating: ${playerStats.chessBullet.best.rating}"
                    mBulletWin.text = "V: ${playerStats.chessBullet.record.win}"
                    mBulletLoss.text = "D: ${playerStats.chessBullet.record.loss}"
                    mBulletDraw.text = "E: ${playerStats.chessBullet.record.draw}"
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
                    Log.e("ChessDebug", e.message.toString())
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