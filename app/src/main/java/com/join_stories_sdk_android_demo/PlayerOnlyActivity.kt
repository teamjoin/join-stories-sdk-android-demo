package com.join_stories_sdk_android_demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.joinstoriessdk.androidsdk.JoinStories
import com.joinstoriessdk.androidsdk.JoinStoriesListener
import kotlinx.android.synthetic.main.activity_player_only.*

class PlayerOnlyActivity : AppCompatActivity(), JoinStoriesListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_only)
        btn_launch_player.setOnClickListener {
            showLoader(true)
            JoinStories.startPlayer(
                "widget-test-sdk",
                lifecycleScope,
                container_view,
                story_player,
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

    override fun onStoryPlayerOnlyDismissed() {
        //You may add logic here
    }

    private fun showLoader(show: Boolean) {
        progress_launch_player.visibility = if (show) View.VISIBLE else View.GONE
    }
}