package com.join_stories_sdk_android_demo

import android.app.Application
import com.joinstoriessdk.androidsdk.JOINStories

const val JOIN_TEAM = "join-showcase-sdk"

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        JOINStories.init(this, JOIN_TEAM)
    }
}