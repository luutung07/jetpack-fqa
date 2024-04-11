package com.tunglv.myapplication

import android.app.Application

private var application: Application? = null

fun getApplication() = application!!

class JetpackFqaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
