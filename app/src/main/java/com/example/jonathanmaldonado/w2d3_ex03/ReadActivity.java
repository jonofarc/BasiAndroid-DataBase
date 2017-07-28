package com.example.jonathanmaldonado.w2d3_ex03;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    private DBHelper helper;
    private SQLiteDatabase database;


    TextView readNoteResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);


        helper = new DBHelper(this);
        database = helper.getWritableDatabase();


        readNoteResult= (TextView) findViewById(R.id.tv_readNoteResult);
    }

    private void readRecord(){

        String[] projection={
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE+"= ?";
        String[] selectionArg = {
                "Record title"
        };
        String sortOtder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE+"DESC";

        Cursor cursor = database.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   //Table
                projection,             //Projection
                null,                   //Selection (WHERE)
                null,                   //Values for selection
                null,                   //Group by
                null,                   //Filters
                null                    //Sort order

        );



        String newMessage="";
        while (cursor.moveToNext()){
            long entryID =cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String entryTitle=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            String entrySubTitle=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));





            newMessage += "Note id: "+ entryID+ " Title: "+ entryTitle+ " Content "+entrySubTitle+"\n";

        }

        readNoteResult.setText("");
        readNoteResult.setText(newMessage);

    }

    public void myReadNote(View view) {
        readRecord();
    }
}
