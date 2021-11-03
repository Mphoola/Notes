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
    @Query("SELECT * FROM notesTable WHERE trashed='0' ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT COUNT(*) FROM notesTable WHERE trashed='0'")
    fun getAllNotesCount() : LiveData<Int>

    @Query("SELECT * FROM notesTable WHERE favourite='1' ORDER BY id DESC")
    fun getAllFavourites(): LiveData<List<Note>>

    @Query("SELECT * FROM notesTable WHERE trashed='1' ORDER BY id DESC")
    fun getAllTrashed(): LiveData<List<Note>>

    @Update
    suspend fun update(note: Note)

    @Query("UPDATE notesTable SET favourite=:favourite WHERE id=:id")
    suspend fun markAsFavourite(id: Int, favourite: String)

    @Query("UPDATE notesTable SET trashed=:trashed WHERE id=:id")
    suspend fun moveToTrash(id: Int, trashed: String)
}