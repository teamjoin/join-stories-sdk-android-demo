package com.join_stories_sdk_android_demo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.join_stories_sdk_android_demo.databinding.ActivityCardBinding

class CardViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardList.apply {
            setStoryPlayer(binding.storyPlayer)
        }

        binding.cardList.startCardView("widget-sdk-test-thumb", 15)

        binding.cardGrid.apply {
            cardHeight = 100
            cardRadius = 10
            hasPlayButton = false
            cardElevation = 8
            setStoryPlayer(binding.storyPlayer)
            configureGridLayout(16, 4)
        }

        binding.cardGrid.startCardView("widget-sdk-test-thumb")

        binding.secondCardGrid.apply {
            cardRadius = 10
            hasOverlay = false
            hasPlayButton = false
            cardElevation = 12
            labelColor = Color.RED
            setStoryPlayer(binding.storyPlayer)
            configureGridLayout(8, 2)
        }

        binding.secondCardGrid.startCardView("widget-sdk-test-thumb")
    }
}