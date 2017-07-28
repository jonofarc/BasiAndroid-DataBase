package com.example.jonathanmaldonado.w2d3_ex03;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    public String deleteNoteTitle;

    private DBHelper helper;
    private SQLiteDatabase database;

    TextView deleteNoteResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();
        if(intent != null){
            deleteNoteTitle = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_EXTRA);

        }

        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        deleteNoteResult= (TextView) findViewById(R.id.tv_deleteResult);


    }

    private void deleteRecords(){
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE +" Like ?";
        //here you add wich record you want to delete
        String[] selectionArgs={
                deleteNoteTitle.toString()

        };
        int deleted = database.delete(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
        if(deleted>0){
            deleteNoteResult.setText("");
            deleteNoteResult.setText("Note Deleted: "+" Title: "+ deleteNoteTitle.toString());

        }else{
            deleteNoteResult.setText("");
            deleteNoteResult.setText("Note Not Deleted: "+" Title: "+ deleteNoteTitle.toString());

        }

    }

    public void myDeleteNote(View view) {
        deleteRecords();
    }
}
