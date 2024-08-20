package com.tausif702.mynotes.databases

import com.tausif702.mynotes.models.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNotes(note: Note)

    @Delete
    suspend fun deleteNotes(note: Note)

    @Query("SELECT * FROM NOTES_TABLE ORDER BY id  DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM NOTES_TABLE WHERE NoteTitle LIKE :query OR NoteDesc LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>


}