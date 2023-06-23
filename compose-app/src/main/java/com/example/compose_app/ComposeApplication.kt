package com.example.compose_app

import android.app.Application
import com.joinstoriessdk.androidsdk.JoinStories

class ComposeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        JoinStories.init(this, "join-showcase")
    }
}