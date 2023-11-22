package br.com.buscadorchesscom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PlayerStatusActivity : AppCompatActivity() {

    private val mText: TextView by lazy { findViewById(R.id.textView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_status)
    }

    override fun onStart() {
        super.onStart()

        val playerName = intent.getStringExtra("playerName")

        mText.text = playerName
    }
}