package com.joinstoriessdk.project.compose_app.ui.screen

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.joinstoriessdk.androidsdk.CardGridConfiguration
import com.joinstoriessdk.androidsdk.CardListConfiguration
import com.joinstoriessdk.androidsdk.CardSize
import com.joinstoriessdk.androidsdk.CardSizeType
import com.joinstoriessdk.androidsdk.JOINStoriesInit
import com.joinstoriessdk.androidsdk.ui.CardTriggerView

/**
 * In this screen, we integrate three card widgets with the same alias (welcome-showcase)
 * but with different ui configurations
 */
@Composable
fun DashboardScreen() {

    val context = LocalContext.current

    val cardList = remember {
        CardTriggerView(context).apply {
            init = JOINStoriesInit("welcome-showcase")
            cardConfiguration = CardListConfiguration(
                cardRadius = 10,
                showPlayButton = false,
                cardElevation = 8
            )
        }
    }

    val cardGrid = remember {
        CardTriggerView(context).apply {
            init = JOINStoriesInit("welcome-showcase")
            cardConfiguration = CardGridConfiguration(
                cardRadius = 10,
                showPlayButton = false,
                cardElevation = 8
            )
        }
    }

    val secondCardGrid = remember {
        CardTriggerView(context).apply {
            init = JOINStoriesInit("welcome-showcase")
            cardConfiguration = CardGridConfiguration(
                cardRadius = 10,
                showOverlay = false,
                showPlayButton = false,
                cardElevation = 12,
                labelColor = Color.RED,
                numberOfColumns = 3
            )
        }
    }

    Scaffold { padding ->
        LazyColumn {
            item {
                AndroidView(
                    factory = { cardList },
                    modifier = Modifier
                        .padding(8.dp)
                        .height(180.dp)
                        .fillMaxWidth()

                )
            }

            item {
                AndroidView(
                    factory = { cardGrid },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            item {
                AndroidView(
                    factory = { secondCardGrid },
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
fun DashboardPreview() {
    DashboardScreen()
}