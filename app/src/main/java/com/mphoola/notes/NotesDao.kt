package com.mphoola.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    //data access object is used to provide an interface
    //to specify sql queries and then associate then with
    //different method calls

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    //writing a custom query to the db
    @Query("SELECT * FROM notesTable ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    suspend fun update(note: Note)
}