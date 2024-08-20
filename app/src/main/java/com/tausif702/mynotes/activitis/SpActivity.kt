package com.tausif702.mynotes.activitis

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tausif702.mynotes.databinding.ActivitySpBinding

class SpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()


        binding = ActivitySpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SpActivity, MainActivity::class.java))
            finish()
        },2500)
        binding.progressBar.visibility=View.GONE

    }
}