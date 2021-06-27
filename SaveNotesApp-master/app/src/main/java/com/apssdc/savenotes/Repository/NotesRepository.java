package com.apssdc.savenotes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.apssdc.savenotes.Dao.NotesDao;
import com.apssdc.savenotes.Database.NotesDatabase;
import com.apssdc.savenotes.Model.Notes;

import java.util.List;

public class NotesRepository {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public NotesRepository(Application application){
        NotesDatabase database=NotesDatabase.getDatabaseInstance(application);
        notesDao= database.notesDao();
        getallNotes = notesDao.getallNotes();
        hightolow=notesDao.hightolow();
        lowtohigh=notesDao.lowtohigh();
    }
    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }
   public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }
   public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
}
