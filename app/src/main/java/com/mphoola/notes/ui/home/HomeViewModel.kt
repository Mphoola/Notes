package com.mphoola.notes.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mphoola.notes.Note
import com.mphoola.notes.NoteDatabase
import com.mphoola.notes.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes : LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun updateNoteFavourite(id:Int, favourite: String ) = viewModelScope.launch(Dispatchers.IO){
        repository.markNoteFavourite(id, favourite)
    }
}