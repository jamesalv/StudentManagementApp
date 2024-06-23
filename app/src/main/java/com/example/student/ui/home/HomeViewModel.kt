package com.example.student.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student.data.Student
import com.example.student.data.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val studentList: List<Student> = listOf()
)

class HomeViewModel(
    studentRepository: StudentRepository
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = studentRepository.getAllStudents().map { HomeUiState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeUiState()
    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}