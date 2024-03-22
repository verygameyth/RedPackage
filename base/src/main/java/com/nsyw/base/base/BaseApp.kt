package com.nsyw.base.base

import android.app.Application

open class BaseApp : Application() {


    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private lateinit var application: BaseApp

        fun getApp(): BaseApp = application
    }
}