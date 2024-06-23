package com.example.student.data

data class OfflineStudentRepository(private val studentDao: StudentDao): StudentRepository
{
    override fun getAllStudents() = studentDao.getAllStudents()
    override fun getStudent(id: Int) = studentDao.getStudent(id)
    override suspend fun insertStudent(student: Student) = studentDao.insert(student)
    override suspend fun updateStudent(student: Student) = studentDao.update(student)
    override suspend fun deleteStudent(student: Student) = studentDao.delete(student)
}
