package me.koltensturgill.sloth.Model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.koltensturgill.sloth.Utils;

// Define our interface for the database operations
@Dao
public interface NoteDao {

    // Insert annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    // Delete all the notes!
    @Query("DELETE FROM notes_table")
    void deleteAll();

    // Get all the notes stored in SQLite db by DESC id
    // This uses LiveData, more here: https://developer.android.com/topic/libraries/architecture/lifecycle

    @Query("SELECT * from notes_table ORDER BY id ASC" +
            "")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * from notes_table ORDER BY id DESC" +
            "")
    LiveData<List<Note>> getAllNotesDesc();

    @Query("SELECT * from notes_table ORDER BY created_at ASC " +
            "")
    LiveData<List<Note>> getAllNotesByDateAsc();

    @Query("SELECT * from notes_table ORDER BY created_at DESC " +
            "")
    LiveData<List<Note>> getAllNotesByDateDesc();

    // TODO: Update
}
