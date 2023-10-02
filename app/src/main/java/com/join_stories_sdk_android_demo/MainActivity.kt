package com.join_stories_sdk_android_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.join_stories_sdk_android_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.btnThumb.setOnClickListener {
            startActivity(Intent(this, ThumbViewsActivity::class.java))
        }

        binding.btnPlayerOnly.setOnClickListener {
            startActivity(Intent(this, PlayerOnlyActivity::class.java))
        }

        binding.btnCard.setOnClickListener {
            startActivity(Intent(this, CardViewActivity::class.java))
        }
    }
    
}