package com.vacral.shopapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin { //TODO делай да }
        }
    }
}