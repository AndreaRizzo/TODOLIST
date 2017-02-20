package com.example.andrearizzo.todolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.andrearizzo.todolist.R;
import com.example.andrearizzo.todolist.adapters.NoteAdapter;
import com.example.andrearizzo.todolist.models.Note;
/**
 * Created by Andrea Rizzo on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_MODE = "ACTION_MODE";
    private static final int REQUEST_ADD = 1001;
    public static final int REQUEST_EDIT = 1002;
    private static final int EDITE_MODE = 1;
    private static final int CREATE_MODE = 2;
    //KEYS
    public static final String NOTE_TITLE_KEY = "NOTE_TITLE_KEY";
    public static final String NOTE_BODY_KEY = "NOTE_BODY_KEY";


    NoteAdapter adapter;
    RecyclerView notesRecyclerView;
    StaggeredGridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notesRecyclerView = (RecyclerView) findViewById(R.id.notes_recycler);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new NoteAdapter(this);

        notesRecyclerView.setLayoutManager(layoutManager);
        notesRecyclerView.setAdapter(adapter);

        registerForContextMenu(notesRecyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditNoteActivity.class);
                i.putExtra(ACTION_MODE, CREATE_MODE);
                startActivityForResult(i, REQUEST_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == Activity.RESULT_OK) {
            Note note = new Note();
            note.setTitle(data.getStringExtra(NOTE_TITLE_KEY));
            note.setBody(data.getStringExtra(NOTE_BODY_KEY));
            adapter.addNote(note);

        }
        if(requestCode == REQUEST_EDIT && resultCode == Activity.RESULT_OK){

            editingNote.setTitle(data.getStringExtra(NOTE_TITLE_KEY));
            editingNote.setTitle(data.getStringExtra(NOTE_BODY_KEY));
            adapter.updateNote(editingNote,adapter.getPosition());
        }
    }

    Note editingNote;

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_delete:
                // remove from adapter
                adapter.removeNote(adapter.getPosition());
                break;
            case R.id.action_edit:

                editingNote = adapter.getNote(adapter.getPosition());
                Intent i = new Intent(this,EditNoteActivity.class);
                i.putExtra(ACTION_MODE,EDITE_MODE);
                i.putExtra(NOTE_TITLE_KEY,editingNote.getTitle());
                i.putExtra(NOTE_BODY_KEY,editingNote.getTitle());
                startActivityForResult(i,REQUEST_EDIT);
                break;

        }

        return super.onContextItemSelected(item);
    }



}