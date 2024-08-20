package com.tausif702.mynotes.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tausif702.mynotes.repository.NoteRepository

class NoteViewModelFuctory(val app:Application,private val noteRepository: NoteRepository):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app,noteRepository) as T
    }

}