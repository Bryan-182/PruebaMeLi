package com.example.meli.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.meli.databinding.ActivitySplashBinding
import com.example.meli.ui.search.SearchActivity
import com.example.meli.utils.Constants.Companion.SPLASH_DISPLAY_LENGTH
import com.example.meli.utils.Message

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivitySplashBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            Handler(Looper.getMainLooper()).postDelayed({
                val mainIntent = Intent(this, SearchActivity::class.java)
                startActivity(mainIntent)
                finish()
            }, SPLASH_DISPLAY_LENGTH.toLong())
        } catch (e: Exception) {
            Message.logMessageException(javaClass, e)
        }
    }
}