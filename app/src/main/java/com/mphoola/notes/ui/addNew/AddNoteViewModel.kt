package com.mphoola.notes.ui.addNew

import android.app.Application
import android.icu.text.SimpleDateFormat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mphoola.notes.Note
import com.mphoola.notes.NoteDatabase
import com.mphoola.notes.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository

    val inputTitle = MutableLiveData<String>()

    val inputDescription = MutableLiveData<String>()

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        inputTitle
        inputDescription
    }

    fun insert(){
        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
        val currentDateAndTime: String = sdf.format(Date())
        val title: String = inputTitle.value!!
        val description: String = inputDescription.value!!
        addNote(Note(title, description, currentDateAndTime))
        inputDescription.value = ""
        inputTitle.value = ""

    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }
}
