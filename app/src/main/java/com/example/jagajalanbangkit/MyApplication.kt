package com.example.jagajalanbangkit

import android.app.Application
import com.example.jagajalanbangkit.components.appcomponents.AppComponent
import com.example.jagajalanbangkit.components.appcomponents.DaggerAppComponent
import com.example.jagajalanbangkit.components.corecomponents.CoreComponent
import com.example.jagajalanbangkit.components.corecomponents.DaggerCoreComponent

class MyApplication : Application() {
    companion object{
        lateinit var coreComponent: CoreComponent
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.factory().create(applicationContext)
        coreComponent.inject(this)
        appComponent = DaggerAppComponent.factory().create(coreComponent)
    }
}