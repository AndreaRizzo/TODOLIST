package com.example.andrearizzo.todolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.andrearizzo.todolist.R;
import com.example.andrearizzo.todolist.database.Databasehandler;

/**
 * Created by Andrea Rizzo on 20/02/2017.
 */

public class EditNoteActivity extends AppCompatActivity {
    EditText titleEditText, bodyEditText;

    Intent intent;

    Databasehandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleEditText = (EditText) findViewById(R.id.title_note_et);
        bodyEditText = (EditText) findViewById(R.id.body_note_et);

        intent = getIntent();


        if (intent != null) {
            if (intent.getIntExtra(MainActivity.ACTION_MODE, 0) == MainActivity.EDIT_MODE) {
                titleEditText.setText(intent.getStringExtra(MainActivity.NOTE_TITLE_KEY));
                bodyEditText.setText(intent.getStringExtra(MainActivity.NOTE_BODY_KEY));
            } else if (intent.getStringExtra(MainActivity.NOTE_BODY_KEY) != null) {
                bodyEditText.setText(intent.getStringExtra(MainActivity.NOTE_BODY_KEY));
            }
        }

        ImageButton fab = (ImageButton) findViewById(R.id.action_share_mod);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d= new Intent();
                String body = intent.getStringExtra(MainActivity.NOTE_BODY_KEY);
                d.setAction(Intent.ACTION_SEND);
                d.putExtra(Intent.EXTRA_TEXT, body);
                d.setType("text/plain");
                startActivity(d);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_confirm) {
            confirmNote();
            return true;
        }
        if (id == android.R.id.home) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void confirmNote() {
        Intent i = new Intent();
        i.putExtra(MainActivity.NOTE_TITLE_KEY, titleEditText.getText().toString());
        i.putExtra(MainActivity.NOTE_BODY_KEY, bodyEditText.getText().toString());
        setResult(Activity.RESULT_OK, i);
        finish();

    }



}