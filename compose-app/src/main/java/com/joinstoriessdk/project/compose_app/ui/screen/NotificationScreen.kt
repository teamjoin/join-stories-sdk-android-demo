package com.joinstoriessdk.project.compose_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joinstoriessdk.androidsdk.JOINStories
import com.joinstoriessdk.androidsdk.PlayerConfiguration
import com.joinstoriessdk.androidsdk.PlayerStandaloneAnimationOrigin
import com.joinstoriessdk.androidsdk.PlayerVerticalAnchor

/**
 * In this screen, we open a story from a button
 */

@Composable
fun NotificationScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            JOINStories.startPlayer(
                context,
                "welcome-showcase",
                configuration = PlayerConfiguration(
                    playerVerticalAnchor = PlayerVerticalAnchor.CENTER,
                    playerStandaloneAnimationOrigin = PlayerStandaloneAnimationOrigin.TOP
                )
            )
        }) {
            Text(text = "Open Story")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    NotificationScreen()
}