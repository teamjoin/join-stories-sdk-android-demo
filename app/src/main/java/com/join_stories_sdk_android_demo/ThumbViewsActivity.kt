package com.join_stories_sdk_android_demo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.join_stories_sdk_android_demo.databinding.ActivityThumbBinding
import com.joinstoriessdk.androidsdk.JoinStories
import com.joinstoriessdk.androidsdk.JoinStoriesListener
import com.joinstoriessdk.androidsdk.config.JoinStoriesThumbConfig
import com.joinstoriessdk.androidsdk.ui.player.OnDismissState

class ThumbViewsActivity : AppCompatActivity(), JoinStoriesListener {

    private lateinit var binding: ActivityThumbBinding

    private val config = JoinStoriesThumbConfig(
        joinAlias = "widget-sdk-test-thumb",
        timeout = 10,
        labelColor = Color.GRAY,
        thumbViewSpacing = 32,
        loaderInnerViewWidth = 6,
        loaderInnerViewColor = intArrayOf(Color.TRANSPARENT),
        loaderColors = intArrayOf(Color.RED, Color.BLUE),
        loaderWidth = 8,
        storyViewedIndicatorAlpha = 80,
        storyViewedIndicatorColor = Color.GRAY,
        thumbViewOverlayColor = Color.parseColor("#BB4C4C4C"),
        playerBackgroundColor = Color.parseColor("#BB000000")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThumbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.storyPlayer.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        JoinStories.startThumbView(
            config,
            lifecycleScope,
            binding.root,
            binding.storyPlayer,
            binding.storyScaffold,
            this
        )
    }

    override fun onBackPressed() {
        if (binding.storyPlayer.isVisible) {
            binding.storyPlayer.dismiss()
        } else {
            super.onBackPressed()
        }
    }

    override fun onStoryFetchError() {
        super.onStoryFetchError()
        Toast.makeText(
            this@ThumbViewsActivity,
            R.string.error_thumb,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStoryFetchEmpty() {
        super.onStoryFetchEmpty()
        Toast.makeText(
            this@ThumbViewsActivity,
            R.string.error_thumb,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStoryPlayerOnlyDismissed(state: OnDismissState) {
        when (state) {
            OnDismissState.OnDismissAuto -> {
                Toast.makeText(this@ThumbViewsActivity, "Dismiss auto", Toast.LENGTH_SHORT)
                    .show()
            }

            OnDismissState.OnDismissManual -> {
                Toast.makeText(this@ThumbViewsActivity, "Dismiss manual", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}