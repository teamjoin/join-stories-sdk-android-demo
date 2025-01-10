package com.join_stories_sdk_android_demo.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.join_stories_sdk_android_demo.databinding.FragmentDashboardBinding
import com.joinstoriessdk.androidsdk.CardGridConfiguration
import com.joinstoriessdk.androidsdk.CardListConfiguration
import com.joinstoriessdk.androidsdk.CardSize
import com.joinstoriessdk.androidsdk.CardSizeType
import com.joinstoriessdk.androidsdk.JOINStoriesInit

/**
 * In this fragment, we integrate three card widgets with the same alias (welcome-showcase)
 * but with different ui configurations
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardList.init = JOINStoriesInit("welcome-showcase")
        binding.cardList.cardConfiguration = CardListConfiguration()

        binding.cardGrid.init = JOINStoriesInit("welcome-showcase")
        binding.cardGrid.cardConfiguration = CardGridConfiguration(
            cardRadius = 10,
            showPlayButton = false,
            cardElevation = 8
        )

        binding.secondCardGrid.init = JOINStoriesInit("welcome-showcase")
        binding.secondCardGrid.cardConfiguration = CardGridConfiguration(
            cardRadius = 10,
            showOverlay = false,
            showPlayButton = false,
            cardElevation = 12,
            labelColor = Color.RED,
            cardHeight = CardSize(CardSizeType.Ratio, 1f),
            numberOfColumns = 3
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}