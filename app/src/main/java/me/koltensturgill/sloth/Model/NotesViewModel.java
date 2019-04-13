package me.koltensturgill.sloth.Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NoteRepository mRepository;
    private LiveData<List<Note>> mAllNotes;
    private LiveData<List<Note>> ascDateAllNotes;
    private LiveData<List<Note>> descDateAllNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
        ascDateAllNotes = mRepository.getAllNotesByDateAsc();
        descDateAllNotes = mRepository.getAllNotesByDateDesc();
    }

    // Getters to abstract request from UI
    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public LiveData<List<Note>> getAscDateAllNotes()
    {
        return ascDateAllNotes;
    }

    public LiveData<List<Note>> getDescDateAllNotes()
    {
        return descDateAllNotes;
    }

    // Wrapper for insert method in repo
    public void insert(Note note) { mRepository.insert(note); }
}
