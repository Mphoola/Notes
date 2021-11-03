package com.mphoola.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    val allNotesCount : LiveData<Int>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotesCount = repository.allNotesCount
    }
}