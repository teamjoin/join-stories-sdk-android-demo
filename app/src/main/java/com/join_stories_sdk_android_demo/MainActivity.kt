package com.join_stories_sdk_android_demo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joinstoriessdk.androidsdk.JoinStories
import com.joinstoriessdk.androidsdk.ui.PlayerStandaloneAnimationOrigin
import com.joinstoriessdk.androidsdk.ui.StoryViewConfig
import kotlinx.android.synthetic.main.activity_main.*

const val JOIN_TEAM = "join-showcase"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        initJoinSDK()
    }

    private fun setupViews() {
        btn_thumb.setOnClickListener {
            startActivity(Intent(this, ThumbViewsActivity::class.java))
        }

        btn_player_only.setOnClickListener {
            startActivity(Intent(this, PlayerOnlyActivity::class.java))
        }
    }

    private fun initJoinSDK() {
        val storyViewConfig = StoryViewConfig(
            // withLabel=false,
            // typeface = Typeface.createFromAsset(assets, "fonts/Lato-BoldItalic.ttf"),
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
            playerStandaloneAnimationOrigin = PlayerStandaloneAnimationOrigin.BOTTOM,
            playerBackgroundColor = Color.parseColor("#BB000000")
        )

        JoinStories.init(JOIN_TEAM, storyViewConfig)
    }
}