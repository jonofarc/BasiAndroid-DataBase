package com.example.jonathanmaldonado.w2d3_ex03;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SaveActivity extends AppCompatActivity {

    private DBHelper helper;
    private SQLiteDatabase database;

    EditText saveNoteTitleET;
    EditText saveNoteContentET;
    TextView saveNoteResult;
  //  TextView saveNoteTitleUpdateET;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        saveNoteResult= (TextView) findViewById(R.id.tv_saveNoteResult);
        saveNoteTitleET= (EditText) findViewById(R.id.et_saveNoteTitle);
        saveNoteContentET= (EditText) findViewById(R.id.et_saveNoteContent);
        //saveNoteTitleUpdateET= (EditText) findViewById(R.id.sa);

    }

    private void saveRecord(){


        String title =saveNoteTitleET.getText().toString();
        String subtitle= saveNoteContentET.getText().toString();
        ContentValues values= new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);
        long recordId = database.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);
        if (recordId>0){
           // Log.d(TAG, "Record Saved");
        }
        saveNoteResult.setText("");
        saveNoteResult.setText("Record Saved: "+" Title: "+ saveNoteTitleET.getText().toString()+ " SubTitle: "+ saveNoteContentET.getText().toString());



    }

    public void mySaveNote(View view) {
        saveRecord();
    }
}
