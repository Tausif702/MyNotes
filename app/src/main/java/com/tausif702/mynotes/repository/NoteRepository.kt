package com.tausif702.mynotes.repository


import com.tausif702.mynotes.databases.NoteDatabase
import com.tausif702.mynotes.models.Note

class NoteRepository(private val db: NoteDatabase) {

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNotes(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNotes(note)
    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String) = db.getNoteDao().searchNote(query)

}