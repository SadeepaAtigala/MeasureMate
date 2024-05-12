package com.example.measuremate.repository

import com.example.measuremate.database.MeasureDatabase
import com.example.measuremate.model.Measure

class MeasureRepository (private val db: MeasureDatabase){

    suspend fun  insertNote(measure: Measure) = db.getNoteDao().insertNote(measure)
    suspend fun  deleteNote(measure: Measure) = db.getNoteDao().deleteNote(measure)
    suspend fun  updateNote(measure: Measure) = db.getNoteDao().updateNote(measure)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)

}