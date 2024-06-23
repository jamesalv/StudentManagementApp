package com.example.student.ui.entry

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.student.data.Student
import com.example.student.data.StudentRepository
import java.sql.Date

class EntryViewModel(private val studentRepository: StudentRepository) : ViewModel() {
    var entryUiState by mutableStateOf(EntryUiState())
        private set

    private fun validateInput(uiState: StudentDetails = entryUiState.studentDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && birthplace.isNotBlank() && birthdate != 0L
                    && (gender == "Male" || gender == "Female") && email.isNotBlank() && phone.isNotBlank()
        }
    }

    // Update the uiState and validate the input
    fun updateUiState(studentDetails: StudentDetails) {
        entryUiState = EntryUiState(studentDetails = studentDetails, isValid = validateInput(studentDetails))
    }

    // Save the student details to the database
    suspend fun saveStudent() {
        if (validateInput()) {
            studentRepository.insertStudent(entryUiState.studentDetails.toStudent())
        }
    }
}

data class StudentDetails(
    val id: Int = 0,
    val name: String = "",
    val birthplace: String = "",
    val birthdate: Long = System.currentTimeMillis(), // Default to current time
    val gender: String = "",
    val email: String = "",
    val phone: String = "",
)

data class EntryUiState(
    val studentDetails: StudentDetails = StudentDetails(),
    val isValid: Boolean = false,
)

// Convert StudentDetails to Student so that it can be saved to the database
fun StudentDetails.toStudent(): Student {
    return Student(
        id = id,
        name = name,
        birthplace = birthplace,
        birthdate = birthdate,
        gender = gender,
        email = email,
        phone = phone,
    )
}

// Convert Student to Details so that it can be displayed
fun Student.toDetails(): StudentDetails = StudentDetails(
    id = id,
    name = name,
    birthplace = birthplace,
    birthdate = birthdate,
    gender = gender,
    email = email,
    phone = phone,
)

// Convert Student to Ui State
fun Student.toUiState(isValid: Boolean =  false): EntryUiState = EntryUiState(
    studentDetails = this.toDetails(),
    isValid = isValid,
)


