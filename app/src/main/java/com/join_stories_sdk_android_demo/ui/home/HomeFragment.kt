package com.join_stories_sdk_android_demo.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.join_stories_sdk_android_demo.databinding.FragmentHomeBinding
import com.joinstoriessdk.androidsdk.BubbleConfiguration
import com.joinstoriessdk.androidsdk.JOINAnalyticsPlayer
import com.joinstoriessdk.androidsdk.JOINAnalyticsTrigger
import com.joinstoriessdk.androidsdk.JOINStoriesAnalyticsListener
import com.joinstoriessdk.androidsdk.JOINStoriesInit
import com.joinstoriessdk.androidsdk.JOINStoriesListener
import com.joinstoriessdk.androidsdk.JOINStoriesPlayerEventType
import com.joinstoriessdk.androidsdk.JOINStoriesTriggerEventType
import com.joinstoriessdk.androidsdk.OnDismissState
import com.joinstoriessdk.androidsdk.PlayerConfiguration
import com.joinstoriessdk.androidsdk.PlayerVerticalAnchor

/**
 * In this fragment, we integrate two bubble widgets with the same alias (welcome-showcase)
 * but with different ui configurations
 *
 * In this example, we add player and analytics listeners
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val joinListener = object : JOINStoriesListener {
        override fun onContentLinkClick(link: String): Boolean {
            return false
        }

        override fun onPlayerDismissed(type: OnDismissState) {
        }

        override fun onPlayerFetchError(errorMessage: String) {
        }

        override fun onPlayerFetchSuccess() {
        }

        override fun onPlayerLoaded() {
        }

        override fun onTriggerFetchEmpty() {
        }

        override fun onTriggerFetchError(errorMessage: String) {
        }

        override fun onTriggerFetchSuccess(itemCount: Int) {
        }
    }

    private val analyticsListener = object : JOINStoriesAnalyticsListener {
        override fun onPlayerAnalyticsCallback(
            event: JOINStoriesPlayerEventType,
            data: JOINAnalyticsPlayer
        ) {

        }

        override fun onTriggerAnalyticsCallback(
            event: JOINStoriesTriggerEventType,
            data: JOINAnalyticsTrigger
        ) {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerConfiguration = PlayerConfiguration(
            playerVerticalAnchor = PlayerVerticalAnchor.CENTER,
            playerHorizontalMargins = 8,
            playerCornerRadius = 30,
            playerProgressBarFillColor = Color.RED,
            playerProgressBarDefaultColor = Color.parseColor("#FFF3AF66"),
            playerBackgroundColor = Color.parseColor("#33AAFFBB"),
            playerProgressBarRadius = 0
        )

        binding.storyScaffold.init = JOINStoriesInit("welcome-showcase")
        binding.storyScaffold.bubbleConfiguration = BubbleConfiguration(
            loaderColors = intArrayOf(Color.RED, Color.CYAN, Color.YELLOW),
            labelColor = Color.WHITE
        )
        binding.storyScaffold.playerConfiguration = playerConfiguration
        binding.storyScaffold.joinListener = joinListener
        binding.storyScaffold.joinAnalyticsListener = analyticsListener

        binding.secondThumbView.init = JOINStoriesInit("welcome-showcase")
        binding.secondThumbView.bubbleConfiguration = BubbleConfiguration(
            loaderColors = intArrayOf(Color.BLUE, Color.GREEN),
            thumbViewSpacing = 20,
            showLabel = false,
            showPlayButton = false
        )
        binding.secondThumbView.joinListener = joinListener
        binding.secondThumbView.joinAnalyticsListener = analyticsListener

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}