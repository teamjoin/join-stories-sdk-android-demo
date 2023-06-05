package com.join_stories_sdk_android_demo

import android.app.Application
import com.joinstoriessdk.androidsdk.JoinStories

const val JOIN_TEAM = "join-showcase"

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        JoinStories.init(JOIN_TEAM)
    }
}