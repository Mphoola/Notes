package com.mphoola.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_screen)

//        splash_image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_in))
//        Handler().postDelayed({
//            splash_image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_out))
//            Handler().postDelayed({
//                splash_image.visibility = View.GONE
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            }, 500)
//        }, 1000)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}