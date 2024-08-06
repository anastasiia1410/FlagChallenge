package com.example.flagchallenge.core

import android.app.Application
import com.example.flagchallenge.di.initKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}