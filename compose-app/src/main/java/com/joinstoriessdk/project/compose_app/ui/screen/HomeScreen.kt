package com.joinstoriessdk.project.compose_app.ui.screen


import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
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
import com.joinstoriessdk.androidsdk.ui.BubbleTriggerView

/**
 * In this screen, we integrate two bubble widgets with the same alias (welcome-showcase)
 * but with different ui configurations
 *
 * In this example, we add player and analytics listeners
 */

private val configuration = PlayerConfiguration(
    playerVerticalAnchor = PlayerVerticalAnchor.CENTER,
    playerHorizontalMargins = 8,
    playerCornerRadius = 30,
    playerProgressBarFillColor = Color.RED,
    playerProgressBarDefaultColor = Color.parseColor("#FFF3AF66"),
    playerBackgroundColor = Color.parseColor("#33AAFFBB"),
    playerProgressBarRadius = 0
)

@Composable
fun HomeScreen() {
    val listener = object : JOINStoriesListener {
        override fun onContentLinkClick(link: String): Boolean {
            return false
        }

        override fun onPlayerDismissed(type: OnDismissState) {
            Log.d("Trigger", "onPlayerDismissed: $type")
        }

        override fun onPlayerFetchError(errorMessage: String) {
            Log.d("Trigger", "onPlayerFetchError: $errorMessage")
        }

        override fun onPlayerFetchSuccess() {
            Log.d("Trigger", "onPlayerFetchSuccess")
        }

        override fun onPlayerLoaded() {
            Log.d("Trigger", "onPlayerLoaded")
        }

        override fun onTriggerFetchEmpty() {
            Log.d("Trigger", "onTriggerFetchEmpty")
        }

        override fun onTriggerFetchError(errorMessage: String) {
            Log.d("Trigger", "onTriggerFetchError: $errorMessage")
        }

        override fun onTriggerFetchSuccess(itemCount: Int) {
            Log.d("Trigger", "onTriggerFetchSuccess: $itemCount")
        }
    }

    val analyticsListener = object : JOINStoriesAnalyticsListener {
        override fun onPlayerAnalyticsCallback(
            event: JOINStoriesPlayerEventType,
            data: JOINAnalyticsPlayer
        ) {
            Log.d("Analytics", "Event: $event, Data: $data")
        }

        override fun onTriggerAnalyticsCallback(
            event: JOINStoriesTriggerEventType,
            data: JOINAnalyticsTrigger
        ) {
            Log.d("Analytics", "Event: $event, Data: $data")
        }
    }

    val context = LocalContext.current

    val bubble = remember {
        BubbleTriggerView(context).apply {
            init = JOINStoriesInit("welcome-showcase")
            playerConfiguration = configuration
            bubbleConfiguration = BubbleConfiguration(
                loaderColors = intArrayOf(Color.BLACK, Color.CYAN, Color.YELLOW),
                labelColor = Color.WHITE
            )
            joinListener = listener
            joinAnalyticsListener = analyticsListener
        }
    }

    val secondBubble = remember {
        BubbleTriggerView(context).apply {
            init = JOINStoriesInit("welcome-showcase")
            bubbleConfiguration = BubbleConfiguration(
                loaderColors = intArrayOf(Color.BLUE, Color.GREEN),
                thumbViewSpacing = 40,
                labelColor = Color.MAGENTA,
                showLabel = false,
                showPlayButton = false
            )
            joinListener = listener
            joinAnalyticsListener = analyticsListener
        }
    }

    Scaffold(
        containerColor = androidx.compose.ui.graphics.Color.Black
    ) { padding ->
        LazyColumn {
            item {
                AndroidView(
                    factory = { bubble },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            item {
                AndroidView(
                    factory = { secondBubble },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}