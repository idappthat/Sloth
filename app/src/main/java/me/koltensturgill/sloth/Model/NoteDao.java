package me.koltensturgill.sloth.Model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

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
    String c = "created_at";
    @Query("SELECT * from notes_table ORDER BY id DESC" +
            "")
    LiveData<List<Note>> getAllNotes();

    // TODO: Update
}
