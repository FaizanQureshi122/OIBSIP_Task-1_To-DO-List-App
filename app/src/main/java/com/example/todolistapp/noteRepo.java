package com.example.todolistapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class noteRepo {

    private NoteDao noteDao;
    private LiveData<List<Note>> noteList;

    public noteRepo(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);

        noteDao = noteDatabase.noteDao();

        noteList = noteDao.getAllData();
    }
    public void insertData(Note note){new InsertTask(noteDao).execute(note);}
    public void updateData(Note note){new UpdateTask(noteDao).execute(note);}
    public void deleteData(Note note){new DeleteTask(noteDao).execute(note);}
    public LiveData<List<Note>>getAllData(){
        return noteList;
    }
        private static class InsertTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        public InsertTask (NoteDao noteDao){
            this.noteDao = noteDao;
        }


            @Override
            protected Void doInBackground(Note... note) {
                noteDao.insert(note[0]);
                return null;
            }
        }

            private static class DeleteTask extends AsyncTask<Note,Void,Void>{
                private NoteDao noteDao;
                public DeleteTask(NoteDao noteDao){
                    this.noteDao = noteDao;
                }

                @Override
                protected Void doInBackground(Note... note) {
                    noteDao.delete(note[0]);
                    return null;
                }
            }
            private static class UpdateTask extends AsyncTask<Note,Void,Void>{
                private NoteDao noteDao;
                public UpdateTask (NoteDao noteDao) {
                    this.noteDao = noteDao;
                }

                @Override
                protected Void doInBackground(Note... note) {
                 noteDao.update(note[0]);
                    return null;
                }
            }

}
