package com.example.student.data

import com.example.student.data.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository
{
    fun getAllStudents(): Flow<List<Student>>
    fun getStudent(id: Int): Flow<Student>
    suspend fun insertStudent(student: Student)
    suspend fun updateStudent(student: Student)
    suspend fun deleteStudent(student: Student)
}