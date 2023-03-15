package com.example.llandroid;

import java.util.ArrayList;
import java.util.Date;

public class Notes {
    // variables used for the Notes code
    public static ArrayList<Notes> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA = "noteEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;

    // method used to show the id, title, description, and date deleted in the SQLite database
    public Notes(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    // method used to show the id, title, and description
    public Notes(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    // method to get the notes ID for the note
    public static Notes getNoteForID(int passedNoteID) {
        for (Notes note : noteArrayList) {
            if (note.getId() == passedNoteID)
                return note;
        }

        return null;
    }

    // method used to store non deleted notes in an array
    public static ArrayList<Notes> nonDeletedNotes() {
        ArrayList<Notes> nonDeleted = new ArrayList<>();
        for (Notes note : noteArrayList) {
            if (note.getDeleted() == null)
                nonDeleted.add(note);
        }

        return nonDeleted;
    }
// getters and setters for the variables at the top of the code
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}