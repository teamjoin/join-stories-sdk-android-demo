package com.join_stories_sdk_android_demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.joinstoriessdk.androidsdk.JoinStories
import com.joinstoriessdk.androidsdk.JoinStoriesListener
import kotlinx.android.synthetic.main.activity_main.container_view
import kotlinx.android.synthetic.main.activity_thumb.*

class ThumbViewsActivity : AppCompatActivity(), JoinStoriesListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thumb)
        story_player.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        JoinStories.startThumbView(
            "demo-bulles",
            lifecycleScope,
            container_view,
            story_player,
            story_scaffold,
            this
        )
    }

    override fun onBackPressed() {
        if (story_player.isVisible) {
            story_player.dismiss()
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
}