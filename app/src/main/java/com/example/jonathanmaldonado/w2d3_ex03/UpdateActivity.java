package com.example.jonathanmaldonado.w2d3_ex03;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {

    public String updateNoteTitle;

    private DBHelper helper;
    private SQLiteDatabase database;

    TextView updateNoteResult;
    EditText updateNoteTitleUpdate;
    EditText updateNoteContentUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        if(intent != null){
            updateNoteTitle = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_EXTRA);

        }

        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        updateNoteResult= (TextView) findViewById(R.id.tv_updateNoteResult);
        updateNoteTitleUpdate= (EditText) findViewById(R.id.et_updateNoteTitleUpdate);
        updateNoteContentUpdate= (EditText) findViewById(R.id.et_updateNoteContentUpdate);

    }


    public void updateRecord(){
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, updateNoteTitleUpdate.getText().toString());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, updateNoteContentUpdate.getText().toString());

        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE+ " LIKE ?";
        String[] selectionArgs={
                updateNoteTitle.toString()
        };

        int count = database.update(

                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        if(count>0){

            updateNoteResult.setText("");
            updateNoteResult.setText("Note Updated: "+" Title: "+ updateNoteTitleUpdate.getText().toString()+ " SubTitle: "+ updateNoteContentUpdate.getText().toString());

        }else{
            updateNoteResult.setText("");
            updateNoteResult.setText("Note Not Updated: "+" Title: "+ updateNoteTitleUpdate.getText().toString()+ " SubTitle: "+ updateNoteContentUpdate.getText().toString());

        }


    }

    public void myUpdateNote(View view) {
        updateRecord();
    }
}
