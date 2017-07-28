package com.example.jonathanmaldonado.w2d3_ex03;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jonathanmaldonado.w2d3_ex03.FeedReaderContract.FeedEntry;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY_EXTRA="com.example.jonathanmaldonado.w2d3_ex03.MAIN_ACTIVITY_EXTRA";

    private static final String TAG = MainActivity.class.getSimpleName()+"_TAG";
    private DBHelper helper;
    private SQLiteDatabase database;

    EditText titleET;
    EditText subTitleET;
    TextView result;
    TextView titleUpdateET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        result= (TextView) findViewById(R.id.tv_result);
        titleET= (EditText) findViewById(R.id.et_title);
        subTitleET= (EditText) findViewById(R.id.et_subtitle);
        titleUpdateET= (EditText) findViewById(R.id.et_titleUpdate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
    /* //Original
    private void saveRecord(){
        String title ="Record Title";
        String subtitle= "Record subtitle";
        ContentValues values= new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,title);
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);
        long recordId = database.insert(FeedEntry.TABLE_NAME,null,values);
        if (recordId>0){
            Log.d(TAG, "Record Saved");
        }
    }
    */
    private void saveRecord(){


        String title =titleET.getText().toString();
        String subtitle= subTitleET.getText().toString();
        ContentValues values= new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,title);
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);
        long recordId = database.insert(FeedEntry.TABLE_NAME,null,values);
        if (recordId>0){
            Log.d(TAG, "Record Saved");
        }
        printMessage("Record Saved: "+" Title: "+ titleET.getText().toString()+ " SubTitle: "+ subTitleET.getText().toString());
        ClearFields();


    }

    @Override
    protected void onResume() {
        super.onResume();
      //  saveRecord();
      //  readRecord();
      //  updateRecord();
      //  readRecord();
      //  deleteRecords();
      //  updateRecord();
    }

    private void readRecord(){

        String[] projection={
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_TITLE,
                FeedEntry.COLUMN_NAME_SUBTITLE
        };
        String selection = FeedEntry.COLUMN_NAME_TITLE+"= ?";
        String[] selectionArg = {
                "Record title"
        };
        String sortOtder = FeedEntry.COLUMN_NAME_SUBTITLE+"DESC";

        Cursor cursor = database.query(
                FeedEntry.TABLE_NAME,   //Table
                projection,             //Projection
                null,                   //Selection (WHERE)
                null,                   //Values for selection
                null,                   //Group by
                null,                   //Filters
                null                    //Sort order

        );



        String newMessage="";
        while (cursor.moveToNext()){
            long entryID =cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID));
            String entryTitle=cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE));
            String entrySubTitle=cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE));
            Log.d(TAG, "readRecord id: "+ entryID+ " Title: "+ entryTitle+ " SubTitle "+entrySubTitle);
            newMessage += "readRecord id: "+ entryID+ " Title: "+ entryTitle+ " SubTitle "+entrySubTitle+"\n";
            printMessage(newMessage);
            //result.setText(result.getText().toString()+"\n"+"readRecord id: "+ entryID+ " Title: "+ entryTitle+ " SubTitle "+entrySubTitle);
        }
    }

    ///////////////////////////////////////Delete///////////////////
    private void deleteRecords(){
        String selection =FeedEntry.COLUMN_NAME_TITLE +" Like ?";
        //here you add wich record you want to delete
        String[] selectionArgs={
          titleET.getText().toString()

        };
        int deleted = database.delete(
                FeedEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
        if(deleted>0){
            Log.d(TAG, "deleted record: Record deleted");
            printMessage("Record Deleted: "+" Title: "+ titleET.getText().toString()+ " SubTitle: "+ subTitleET.getText().toString());
            ClearFields();
        }else{
            Log.d(TAG, "deleted record: Record not deleted");
            printMessage("Record not deleted: ");
           // ClearFields();
        }

    }

    public void updateRecord(){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE, titleUpdateET.getText().toString());

        String selection = FeedEntry.COLUMN_NAME_TITLE+ " LIKE ?";
        String[] selectionArgs={
                titleET.getText().toString()
        };

        int count = database.update(

                FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        if(count>0){
            Log.d(TAG, "Update record: Updated records "+count);
            printMessage("Record updated: "+" Title: "+ titleUpdateET.getText().toString());
            ClearFields();
        }else{
            Log.d(TAG, "Update record: Updated not records "+count);
            printMessage("Record Not updated: ");
           // ClearFields();
        }


    }

    public void mySaveRecord(View view) {

        //saveRecord();
        Intent intent = new Intent(MainActivity.this , SaveActivity.class);
        startActivity(intent);
    }

    public void myReadRecord(View view) {
        //readRecord();
        Intent intent = new Intent(MainActivity.this , ReadActivity.class);
        startActivity(intent);
    }

    public void myUpdateRecord(View view) {

        //updateRecord();
        Intent intent = new Intent(MainActivity.this , UpdateActivity.class);
        String message=titleET.getText().toString();
        intent.putExtra(MAIN_ACTIVITY_EXTRA, message);
        startActivity(intent);
    }

    public void myDeleteRecord(View view) {

       // deleteRecords();
        Intent intent = new Intent(MainActivity.this , DeleteActivity.class);
        String message=titleET.getText().toString();
        intent.putExtra(MAIN_ACTIVITY_EXTRA, message);
        startActivity(intent);
    }
    public void ClearFields(){
        subTitleET.setText("");
        titleET.setText("");
        titleUpdateET.setText("");
    }
    public void printMessage(String message){
        result.setText("");
        result.setText(message);
    }
}
