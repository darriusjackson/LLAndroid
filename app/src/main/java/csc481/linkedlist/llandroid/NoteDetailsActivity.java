package csc481.linkedlist.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.Date;

public class NoteDetailsActivity extends AppCompatActivity
{
    //variables for the NoteDetailsActivity code
    private EditText titleEditText, descEditText;
    private Button deleteButton;
    private Notes selectedNote;

    // sets the content with the notes activity details and initializes methods used in the code
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        initWidgets();
        checkForEditNote();
    }

    // the variables are assigned to certain IDs using findViewById()
    private void initWidgets()
    {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteNoteButton);
    }

    // method used to checked for the edited notes
    private void checkForEditNote()
    {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(Notes.NOTE_EDIT_EXTRA, -1);
        selectedNote = Notes.getNoteForID(passedNoteID);

        if (selectedNote != null)
        {
            titleEditText.setText(selectedNote.getTitle());
            descEditText.setText(selectedNote.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    // saves the notes the user created and adds/updates it into the SQLite database
    public void saveNote(View view)
    {
        NotesDBManager sqLiteManager = NotesDBManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedNote == null)
        {
            int id = Notes.noteArrayList.size();
            Notes newNote = new Notes(id, title, desc);
            Notes.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
        }
        else
        {
            selectedNote.setTitle(title);
            selectedNote.setDescription(desc);
            sqLiteManager.updateNoteInDB(selectedNote);
        }

        finish();
    }

    // method used to delete the note in the SQLite database
    public void deleteNote(View view)
    {
        selectedNote.setDeleted(new Date());
        NotesDBManager sqLiteManager = NotesDBManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }
}