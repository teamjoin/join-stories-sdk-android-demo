package com.example.compose_app

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.compose_app.ui.theme.JoinStoriesActionButton
import com.example.compose_app.ui.theme.JoinStoriesBlack
import com.example.compose_app.ui.theme.JoinStoriesSDKDemoTheme
import com.joinstoriessdk.androidsdk.ui.StoryScaffoldView
import com.joinstoriessdk.androidsdk.ui.player.StoryPlayer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoinStoriesSDKDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = JoinStoriesBlack
                ) {

                    val storyPlayerView = remember { StoryPlayer(this) }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        StandalonePlayer(storyPlayerView)
                        ThumbView(storyPlayerView)
                    }

                    AndroidView(
                        factory = {
                            storyPlayerView
                        },
                        update = {
                            it.visibility = View.GONE
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StandalonePlayer(player: StoryPlayer) {
    Button(
        onClick = {
            player.startPlayer("widget-sdk-test-standalone")
        },
        colors = ButtonDefaults.buttonColors(containerColor = JoinStoriesActionButton),
    )
    {
        Text(text = "Start Player")
    }
}

@Composable
fun ThumbView(storyPlayerView: StoryPlayer) {
    val context = LocalContext.current
    val storyScaffoldView = remember { StoryScaffoldView(context) }
    val secondStoryScaffoldView = remember { StoryScaffoldView(context) }

    AndroidView(
        factory = {
            storyScaffoldView.apply {
                thumbViewSpacing = 32
                withLabel = true
                labelColor = Color.WHITE
                setStoryPlayer(storyPlayerView)
            }
        },
        modifier = Modifier
            .requiredHeight(150.dp)
            .fillMaxWidth()
    )

    AndroidView(
        factory = {
            secondStoryScaffoldView.apply {
                thumbViewSpacing = 32
                withLabel = true
                labelColor = Color.WHITE
                setStoryPlayer(storyPlayerView)
            }
        },
        modifier = Modifier
            .requiredHeight(150.dp)
            .fillMaxWidth()
    )

    StartThumbViewCompose(storyScaffoldView, joinAlias = "widget-sdk-test-thumb")
    StartThumbViewCompose(secondStoryScaffoldView, joinAlias = "widget-test-sdk")
}

@Composable
fun StartThumbViewCompose(
    storyScaffoldView: StoryScaffoldView,
    joinAlias: String,
    timeout: Long = 10
) {
    LaunchedEffect(Unit) {
        storyScaffoldView.startThumbView(joinAlias, timeout)
    }
}