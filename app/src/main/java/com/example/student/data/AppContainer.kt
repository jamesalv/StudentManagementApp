package com.example.student.data

import android.content.Context

interface AppContainer {
    val studentsRepository: StudentRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val studentsRepository: StudentRepository by lazy {
        OfflineStudentRepository(StudentDatabase.getDatabase(context).studentDao())
    }
}