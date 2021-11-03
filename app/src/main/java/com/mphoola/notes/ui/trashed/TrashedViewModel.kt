package com.mphoola.notes.ui.trashed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mphoola.notes.Note
import com.mphoola.notes.NoteDatabase
import com.mphoola.notes.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrashedViewModel(application: Application) : AndroidViewModel(application) {
    val allTrashedNotes : LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allTrashedNotes = repository.allTrashed
    }

    fun updateNoteFavourite(id:Int, favourite: String ) = viewModelScope.launch(Dispatchers.IO){
        repository.markNoteFavourite(id, favourite)
    }
}