package com.join_stories_sdk_android_demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.join_stories_sdk_android_demo.databinding.ActivityPlayerOnlyBinding
import com.joinstoriessdk.androidsdk.JoinStoriesListener
import com.joinstoriessdk.androidsdk.config.PlayerStandaloneAnimationOrigin
import com.joinstoriessdk.androidsdk.config.PlayerVerticalAnchor
import com.joinstoriessdk.androidsdk.ui.player.OnDismissState

class PlayerOnlyActivity : AppCompatActivity(), JoinStoriesListener {

    private lateinit var binding: ActivityPlayerOnlyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerOnlyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLaunchPlayer.setOnClickListener {
            showLoader(true)

            binding.storyPlayer.startPlayer("widget-sdk-test-standalone", timeout = 15)
        }

        binding.storyPlayer.apply {
            playerVerticalAnchor = PlayerVerticalAnchor.BOTTOM
            joinStoriesListener = this@PlayerOnlyActivity
            animationOrigin = PlayerStandaloneAnimationOrigin.TOP_RIGHT
        }

    }

    override fun onStoryPlayerOnlyLoaded() {
        showLoader(false)
    }

    override fun onStoryFetchError() {
        Toast.makeText(this@PlayerOnlyActivity, "Error player", Toast.LENGTH_SHORT)
            .show()
        showLoader(false)
    }

    override fun onStoryFetchEmpty() {
        Toast.makeText(this@PlayerOnlyActivity, "empty view", Toast.LENGTH_SHORT)
            .show()
        showLoader(false)
    }

    override fun onStoryPlayerOnlyDismissed(state: OnDismissState) {
        //You may add logic here
        when (state) {
            OnDismissState.OnDismissAuto -> {
                Toast.makeText(this@PlayerOnlyActivity, "Dismiss auto", Toast.LENGTH_SHORT)
                    .show()
            }

            OnDismissState.OnDismissManual -> {
                Toast.makeText(this@PlayerOnlyActivity, "Dismiss manual", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showLoader(show: Boolean) {
        binding.progressLaunchPlayer.isVisible = show
    }
}