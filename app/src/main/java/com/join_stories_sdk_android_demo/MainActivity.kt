package com.join_stories_sdk_android_demo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.joinstoriessdk.androidsdk.api.StoriesViewModel
import com.joinstoriessdk.androidsdk.stories.StoryValue
import com.joinstoriessdk.androidsdk.ui.StoryScaffoldListener
import com.joinstoriessdk.androidsdk.ui.StoryViewConfig
import kotlinx.android.synthetic.main.activity_main.*

const val JOIN_TEAM = "join-showcase"
const val JOIN_ALIAS = "widget-test-sdk"


class MainActivity : AppCompatActivity(), StoryScaffoldListener {

    private lateinit var viewModel: StoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        story_player.visibility = View.GONE

        viewModel = StoriesViewModel()
        viewModel.state.observe(this, Observer<List<StoryValue>> { stories ->
            val storyViewConfig = StoryViewConfig(
                // withLabel=false,
                // typeface = Typeface.createFromAsset(assets, "fonts/Lato-BoldItalic.ttf"),
                labelColor = Color.GRAY,
                thumbViewSpacing = 32,
                loaderInnerViewWidth = 6,
                loaderInnerViewColor = intArrayOf(Color.TRANSPARENT),
                loaderColors = intArrayOf(Color.RED, Color.BLUE),
                loaderWidth = 8,
                storyViewedIndicatorAlpha = 80,
                storyViewedIndicatorColor = Color.GRAY,
                thumbViewOverlayColor = Color.parseColor("#BB4C4C4C")
            )
            story_scaffold
                .withConfig(storyViewConfig)
                .buildScroller(stories, this)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStories(JOIN_TEAM, JOIN_ALIAS)
    }

    override fun onStorySelected(stories: List<StoryValue>, story: StoryValue, thumbView: View) {
        //Mandatory call
        story_player.loadStories(stories, story, thumbView, container_view, story_scaffold)
    }

    override fun onBackPressed() {
        if (story_player.isVisible) {
            story_player.dismiss()
        } else {
            super.onBackPressed()
        }
    }
}