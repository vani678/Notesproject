package com.apssdc.savenotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.apssdc.savenotes.Dao.NotesDao;
import com.apssdc.savenotes.Model.Notes;
import com.apssdc.savenotes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public NotesViewModel(Application application) {
        super(application);
        repository=new NotesRepository(application);
        getAllNotes=repository.getallNotes;
        hightolow=repository.hightolow;
        lowtohigh=repository.lowtohigh;
    }
    public void insertNote(Notes notes){
        repository.insertNotes(notes);
    }
    public void deleteNote(int id){
        repository.deleteNotes(id);
    }
    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }
}
