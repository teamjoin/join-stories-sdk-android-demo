package com.join_stories_sdk_android_demo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.join_stories_sdk_android_demo.databinding.ActivityPlayerOnlyBinding
import com.joinstoriessdk.androidsdk.JoinStories
import com.joinstoriessdk.androidsdk.JoinStoriesListener
import com.joinstoriessdk.androidsdk.config.JoinStoriesStandaloneConfig
import com.joinstoriessdk.androidsdk.config.PlayerVerticalAnchor
import com.joinstoriessdk.androidsdk.ui.player.OnDismissState

class PlayerOnlyActivity : AppCompatActivity(), JoinStoriesListener {

    private lateinit var binding: ActivityPlayerOnlyBinding

    private val config = JoinStoriesStandaloneConfig(
        joinAlias = "widget-sdk-test-standalone",
        playerBackgroundColor = Color.parseColor("#BB000000"),
        playerVerticalAnchor = PlayerVerticalAnchor.CENTER,
        playerShowShareButton = false,
        playerCornerRadius = 30f,
        playerProgressBarThickness = 4,
        playerProgressBarDefaultColor = "#FFFFFF66",
        playerProgressBarFillColor = "#026eda",
        playerProgressBarRadius = 8,
        playerHorizontalMargin = 20
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerOnlyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLaunchPlayer.setOnClickListener {
            showLoader(true)
            JoinStories.startPlayer(
                config,
                lifecycleScope,
                binding.root,
                binding.storyPlayer,
                this
            )
        }
    }

    override fun onStoryPlayerOnlyLoaded() {
        showLoader(false)
    }

    override fun onStoryFetchError() {
        Toast.makeText(this@PlayerOnlyActivity, R.string.error_player, Toast.LENGTH_SHORT)
            .show()
        showLoader(false)
    }

    override fun onStoryPlayerOnlyDismissed(state: OnDismissState) {
        //You may add logic here
    }

    private fun showLoader(show: Boolean) {
        binding.progressLaunchPlayer.isVisible = show
    }
}