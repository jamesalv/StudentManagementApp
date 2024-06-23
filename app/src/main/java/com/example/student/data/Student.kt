package com.example.student.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val birthplace: String,
    val birthdate: Long,
    val gender: String,
    val email: String,
    val phone: String,
)
