package com.join_stories_sdk_android_demo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.join_stories_sdk_android_demo.databinding.ActivityThumbBinding
import com.joinstoriessdk.androidsdk.JoinStoriesAnalyticsCallback
import com.joinstoriessdk.androidsdk.JoinStoriesEventType
import com.joinstoriessdk.androidsdk.JoinStoriesListener
import com.joinstoriessdk.androidsdk.StoryException
import com.joinstoriessdk.androidsdk.config.PlayerStandaloneAnimationOrigin
import com.joinstoriessdk.androidsdk.config.PlayerVerticalAnchor
import com.joinstoriessdk.androidsdk.data.model.AnalyticsWidgetValue
import com.joinstoriessdk.androidsdk.ui.player.OnDismissState

class ThumbViewsActivity : AppCompatActivity(), JoinStoriesListener, JoinStoriesAnalyticsCallback {

    private lateinit var binding: ActivityThumbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThumbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.storyPlayer.apply {
            playerVerticalAnchor = PlayerVerticalAnchor.CENTER
            animationOrigin = PlayerStandaloneAnimationOrigin.BOTTOM
            playerBackgroundColor = Color.parseColor("#33AAFFBB")
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
            analyticsCallback = this@ThumbViewsActivity
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
            analyticsCallback = this@ThumbViewsActivity
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

    override fun onStoryFetchError(e: StoryException) {
        super.onStoryFetchError(e)
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

    override fun onStoryFetchSuccess() {
        super.onStoryFetchSuccess()
        Toast.makeText(
            this@ThumbViewsActivity,
            "fetch success",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStoryLinkClick(link: String) {
        super.onStoryLinkClick(link)
        Toast.makeText(
            this@ThumbViewsActivity,
            "link clicked: $link",
            Toast.LENGTH_SHORT
        ).show()
        binding.storyPlayer.dismiss()
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

    override fun onAnalyticsCallback(event: JoinStoriesEventType, data: AnalyticsWidgetValue) {
        when(event) {
            JoinStoriesEventType.AdditionalClickOnWidget -> Log.d("ThumbViewsActivity", "AdditionalClickOnWidget")
            JoinStoriesEventType.ComponentVisible50 -> Log.d("ThumbViewsActivity", "ComponentVisible50")
            JoinStoriesEventType.ComponentVisible75 -> Log.d("ThumbViewsActivity", "ComponentVisible75")
            JoinStoriesEventType.FirstClickOnWidget -> Log.d("ThumbViewsActivity", "FirstClickOnWidget")
            JoinStoriesEventType.StoriesFetched -> Log.d("ThumbViewsActivity", "StoriesFetched")
            JoinStoriesEventType.WidgetMounted -> Log.d("ThumbViewsActivity", "WidgetMounted")
        }
    }
}