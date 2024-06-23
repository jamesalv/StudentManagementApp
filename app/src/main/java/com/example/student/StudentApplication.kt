package com.example.student

import android.app.Application
import com.example.student.data.AppContainer
import com.example.student.data.AppDataContainer

class StudentApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}