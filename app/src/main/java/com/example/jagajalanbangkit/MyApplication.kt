package com.example.jagajalanbangkit

import android.app.Application
import com.example.jagajalanbangkit.components.appcomponents.AppComponent
import com.example.jagajalanbangkit.components.corecomponents.CoreComponent

class MyApplication : Application() {
    companion object{
        lateinit var coreComponent: CoreComponent
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
    }
}