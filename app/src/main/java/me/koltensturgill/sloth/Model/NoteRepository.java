package me.koltensturgill.sloth.Model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {
    // Member variables
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;
    private LiveData<List<Note>> mAllNotesDesc;
    private LiveData<List<Note>> ascDateAllNotes;
    private LiveData<List<Note>> descDateAllNotes;

    // Constructor
    NoteRepository(Application application){
        AppRoomDatabase db = AppRoomDatabase.getInstance(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
        mAllNotesDesc = mNoteDao.getAllNotesDesc();
        ascDateAllNotes = mNoteDao.getAllNotesByDateAsc();
        descDateAllNotes = mNoteDao.getAllNotesByDateDesc();
    }

    // Wrapper to get notes
    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    LiveData<List<Note>> getAllNotesDesc() {
        return mAllNotesDesc;
    }

    // Wrapper to get notes ordered asc by date created at
    LiveData<List<Note>> getAllNotesByDateAsc()
    {
        return ascDateAllNotes;
    }

    // Wrapper to get notes ordered desc by date created at
    LiveData<List<Note>> getAllNotesByDateDesc()
    {
        return descDateAllNotes;
    }

    // Wrapper function for inserting
    public void insert(Note note){
        new insertTask(mNoteDao).execute(note);
    }

    // AsyncTask lets us preform background operations so we don't hog the main UI thread
    private static class insertTask extends AsyncTask<Note, Void, Void> {

        // data access object instance
        private NoteDao mAsyncNoteDao;

        insertTask(NoteDao dao){
            mAsyncNoteDao = dao;
        }


        // Override function that does the processing
        @Override
        protected Void doInBackground(final Note... note) {
            // access the "first" object, could insert multiple object in the future
            mAsyncNoteDao.insert(note[0]);
            return null;
        }
    }
}
