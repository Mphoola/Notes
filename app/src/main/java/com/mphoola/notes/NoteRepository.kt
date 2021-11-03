package com.mphoola.notes

import androidx.lifecycle.LiveData

//rep is where you specify whether to get data from room db or API
class NoteRepository(private val notesDao: NotesDao) {
    //getting all notes from our dao class
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()
    val allTrashed: LiveData<List<Note>> = notesDao.getAllTrashed()
    val allFavourites: LiveData<List<Note>> = notesDao.getAllFavourites()
    val allNotesCount: LiveData<Int> = notesDao.getAllNotesCount()

    //for adding note to database
    suspend fun insert(note: Note){
        notesDao.insert(note)
    }
    //delete
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }

    suspend fun markNoteFavourite(id: Int, favourite: String){
        notesDao.markAsFavourite(id, favourite)
    }
}