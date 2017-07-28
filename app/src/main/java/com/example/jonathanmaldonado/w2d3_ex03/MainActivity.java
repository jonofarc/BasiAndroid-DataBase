package com.example.jonathanmaldonado.w2d3_ex03;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY_EXTRA="com.example.jonathanmaldonado.w2d3_ex03.MAIN_ACTIVITY_EXTRA";


    private DBHelper helper;
    private SQLiteDatabase database;

    EditText titleET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        database = helper.getWritableDatabase();


        titleET= (EditText) findViewById(R.id.et_title);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    public void mySaveRecord(View view) {

        Intent intent = new Intent(MainActivity.this , SaveActivity.class);
        startActivity(intent);
    }

    public void myReadRecord(View view) {

        Intent intent = new Intent(MainActivity.this , ReadActivity.class);
        startActivity(intent);
    }

    public void myUpdateRecord(View view) {


        Intent intent = new Intent(MainActivity.this , UpdateActivity.class);
        String message=titleET.getText().toString();
        intent.putExtra(MAIN_ACTIVITY_EXTRA, message);
        startActivity(intent);
    }

    public void myDeleteRecord(View view) {


        Intent intent = new Intent(MainActivity.this , DeleteActivity.class);
        String message=titleET.getText().toString();
        intent.putExtra(MAIN_ACTIVITY_EXTRA, message);
        startActivity(intent);
    }

}
