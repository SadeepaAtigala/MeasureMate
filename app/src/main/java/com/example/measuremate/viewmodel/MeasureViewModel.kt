package com.example.measuremate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.measuremate.model.Measure
import com.example.measuremate.repository.MeasureRepository
import kotlinx.coroutines.launch

class MeasureViewModel(app: Application, private val measureRepository: MeasureRepository): AndroidViewModel(app) {

    fun addNote(measure: Measure)=
        viewModelScope.launch {
            measureRepository.insertNote(measure)
        }
    fun deleteNote(measure: Measure)=
        viewModelScope.launch {
            measureRepository.deleteNote(measure)
        }
    fun updateNote(measure: Measure)=
        viewModelScope.launch {
            measureRepository.updateNote(measure)
        }

    fun getAllNotes() = measureRepository.getAllNotes()

    fun searchNote(query: String?)=
        measureRepository.searchNote((query))
}