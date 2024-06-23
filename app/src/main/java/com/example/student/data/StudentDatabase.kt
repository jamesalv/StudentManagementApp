package com.example.student.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase: RoomDatabase()
{
    abstract fun studentDao(): StudentDao
    companion object{
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, StudentDatabase::class.java, "student_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}