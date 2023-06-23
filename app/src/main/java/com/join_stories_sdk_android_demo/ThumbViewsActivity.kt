package com.join_stories_sdk_android_demo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.join_stories_sdk_android_demo.databinding.ActivityThumbBinding
import com.joinstoriessdk.androidsdk.JoinStoriesListener
import com.joinstoriessdk.androidsdk.R
import com.joinstoriessdk.androidsdk.config.PlayerStandaloneAnimationOrigin
import com.joinstoriessdk.androidsdk.config.PlayerVerticalAnchor
import com.joinstoriessdk.androidsdk.ui.player.OnDismissState

class ThumbViewsActivity : AppCompatActivity(), JoinStoriesListener {

    private lateinit var binding: ActivityThumbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThumbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.storyPlayer.apply {
            playerVerticalAnchor = PlayerVerticalAnchor.CENTER
            animationOrigin = PlayerStandaloneAnimationOrigin.BOTTOM
            playerBackgroundColor = Color.TRANSPARENT
            playerShowShareButton = true
            playerClosingButton = true
            playerCornerRadius = 30f
            playerProgressBarDefaultColor = "#FFF3AF66"
            playerProgressBarRadius = 0
        }

        // Other parameters are configured in the xml view
        binding.storyScaffold.apply {
            withLabel = true
            loaderInnerViewColor = intArrayOf(Color.RED, Color.CYAN, Color.YELLOW)
            loaderColors = intArrayOf(Color.GREEN)
            joinStoriesListener = this@ThumbViewsActivity
        }
        binding.storyScaffold.setStoryPlayer(binding.storyPlayer)
        binding.storyScaffold.startThumbView("widget-sdk-test-thumb")


        binding.secondThumbView.apply {
            withLabel = true
            labelColor = Color.MAGENTA
            thumbViewSpacing = 20
            thumbViewOverlayColor = Color.parseColor("#BB4F4C")
            loaderInnerViewColor = intArrayOf(Color.LTGRAY, Color.GREEN, Color.YELLOW)
            loaderInnerViewColor = intArrayOf(Color.RED, Color.BLUE)
        }
        binding.secondThumbView.setStoryPlayer(binding.storyPlayer)
        binding.secondThumbView.startThumbView("widget-sdk-test-standalone")
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
            "Error thumb",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStoryFetchEmpty() {
        super.onStoryFetchEmpty()
        Toast.makeText(
            this@ThumbViewsActivity,
            "empty view",
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