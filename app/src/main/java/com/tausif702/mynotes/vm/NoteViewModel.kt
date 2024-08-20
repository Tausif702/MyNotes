package com.tausif702.mynotes.vm


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tausif702.mynotes.models.Note
import com.tausif702.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app:Application,private val noteRepository: NoteRepository):AndroidViewModel(app) {
    fun addNotes(note: Note)=viewModelScope.launch {
        noteRepository.insertNote(note)
    }


    fun deleteNotes(note: Note)=viewModelScope.launch {
        noteRepository.deleteNote(note)
    }


    fun updateNotes(note: Note)=viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun getAllNotes()=noteRepository.getAllNotes()

    fun searchNotes(query:String?)=noteRepository.searchNote(query.toString())
}