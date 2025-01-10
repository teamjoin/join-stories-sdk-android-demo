package com.join_stories_sdk_android_demo.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.join_stories_sdk_android_demo.databinding.FragmentNotificationsBinding
import com.joinstoriessdk.androidsdk.JOINStories
import com.joinstoriessdk.androidsdk.PlayerConfiguration
import com.joinstoriessdk.androidsdk.PlayerStandaloneAnimationOrigin
import com.joinstoriessdk.androidsdk.PlayerVerticalAnchor

/**
 * In this fragment, we open a story from a button
 */
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = binding.storyButton
        button.setOnClickListener {
            JOINStories.startPlayer(
                requireContext(),
                "welcome-showcase",
                configuration = PlayerConfiguration(
                    playerVerticalAnchor = PlayerVerticalAnchor.CENTER,
                    playerStandaloneAnimationOrigin = PlayerStandaloneAnimationOrigin.TOP
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}