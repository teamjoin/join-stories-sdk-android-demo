package com.join_stories_sdk_android_demo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

import com.joinstoriessdk.androidsdk.stories.StoryValue
import com.joinstoriessdk.androidsdk.ui.StoryScaffoldListener
import com.joinstoriessdk.androidsdk.ui.StoryViewConfig
import com.joinstoriessdk.androidsdk.api.StoriesViewModel

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
                dividerWidth = 50,
                innerBorderWidth = 2,
                outterBorderWidth = 3,
                innerBorderColor = intArrayOf(Color.WHITE),
                outterBorderColor = intArrayOf(Color.RED, Color.BLUE)
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

    override fun onStorySelected(story: StoryValue) {
        story_player.loadStory(story)
    }

}