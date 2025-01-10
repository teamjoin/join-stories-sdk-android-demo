package com.joinstoriessdk.project.compose_app

import android.app.Application
import com.joinstoriessdk.androidsdk.JOINStories

const val JOIN_TEAM = "join-showcase-sdk"

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        JOINStories.init(this, JOIN_TEAM)
    }
}