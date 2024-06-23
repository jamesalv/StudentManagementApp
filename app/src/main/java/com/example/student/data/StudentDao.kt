package com.example.student.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.student.data.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    // Get student with certain id
    @Query("SELECT * from students WHERE id = :id")
    fun getStudent(id: Int): Flow<Student>

    // Get all students
    @Query("SELECT * from students ORDER BY id ASC")
    fun getAllStudents(): Flow<List<Student>>
}