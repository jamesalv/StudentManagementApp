package com.example.student

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.student.ui.edit.EditViewModel
import com.example.student.ui.entry.EntryViewModel
import com.example.student.ui.home.HomeViewModel

fun CreationExtras.studentApplication(): StudentApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as StudentApplication)

object AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            EntryViewModel(studentApplication().container.studentsRepository)
        }

        initializer {
            HomeViewModel(studentApplication().container.studentsRepository)
        }

        initializer{
            EditViewModel(
                this.createSavedStateHandle(),
                studentApplication().container.studentsRepository
            )
        }
    }
}