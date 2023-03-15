package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NotesActivity extends AppCompatActivity {
    // variable for the NotesActivity code
    private ListView noteListView;

    // sets the content with the notes activity and initializes methods used in the code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();
    }


    // the variable are assigned to certain IDs using findViewById()
    private void initWidgets() {
        noteListView = findViewById(R.id.noteListView);
    }

    // method to save the notes from the database into the memory
    private void loadFromDBToMemory() {
        NotesDBManager sqLiteManager = NotesDBManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }
// method to set up the notes adapter
    private void setNoteAdapter() {
        NotesAdapter noteAdapter = new NotesAdapter(getApplicationContext(), Notes.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }

// method to go from the Notes page to Edit Notes page by pressing the plus button
    private void setOnClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Notes selectedNote = (Notes) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), NoteDetailsActivity.class);
                editNoteIntent.putExtra(Notes.NOTE_EDIT_EXTRA, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

// method used to display the new notes
    public void newNote(View view) {
        Intent newNoteIntent = new Intent(this, NoteDetailsActivity.class);
        startActivity(newNoteIntent);
    }

    // sets up the notes adapter
    @Override
    protected void onResume() {
        super.onResume();
        setNoteAdapter();
    }
}