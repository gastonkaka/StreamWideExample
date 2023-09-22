package com.ghassen.streamwideexample


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // our window to full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.splash_screen)

        // handler to run a task
        // for specific time
        Handler().postDelayed({
            // creating a new intent
            val i = Intent(
                this@SplashActivity,
                MainActivity::class.java
            )

            // starting a new activity.
            startActivity(i)

            finish()
        }, 2000)
    }
}
