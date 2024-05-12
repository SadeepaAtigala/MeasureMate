package com.example.measuremate.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.measuremate.model.Measure


@Dao
interface MeasureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(measure: Measure)

    @Update
    suspend fun updateNote(measure: Measure)

    @Delete
    suspend fun deleteNote(measure: Measure)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Measure>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteDesc LIKE :query")
    fun searchNote(query: String?): LiveData<List<Measure>>



}