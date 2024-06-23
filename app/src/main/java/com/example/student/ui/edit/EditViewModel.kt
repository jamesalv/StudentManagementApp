package com.example.student.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student.data.StudentRepository
import com.example.student.ui.entry.EntryUiState
import com.example.student.ui.entry.StudentDetails
import com.example.student.ui.entry.toStudent
import com.example.student.ui.entry.toUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel() {
    var studentUiState by mutableStateOf(EntryUiState())
        private set

    private val studentId: Int = checkNotNull(savedStateHandle[EditDestination.ID])
    init {
        viewModelScope.launch {
            studentUiState = studentRepository.getStudent(studentId)
                .filterNotNull()
                .first()
                .toUiState(true)
        }
    }
    private fun validateInput(uiState: StudentDetails = studentUiState.studentDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && birthplace.isNotBlank() && birthdate != 0L
                    && (gender == "Male" || gender == "Female") && email.isNotBlank() && phone.isNotBlank()
        }
    }

    fun updateUiState(studentDetails: StudentDetails){
        studentUiState = EntryUiState(studentDetails = studentDetails, isValid = validateInput(studentDetails))
    }

    suspend fun update() {
        if(validateInput(studentUiState.studentDetails)){
            studentRepository.updateStudent(studentUiState.studentDetails.toStudent())
        }
    }

    suspend fun delete(){
        studentRepository.deleteStudent(studentUiState.studentDetails.toStudent())
    }
}