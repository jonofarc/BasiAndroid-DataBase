package com.example.jonathanmaldonado.w2d3_ex03;

import android.provider.BaseColumns;
import android.widget.EditText;

/**
 * Created by Jonathan Maldonado on 7/26/2017.
 */

public final class FeedReaderContract {

    private FeedReaderContract(){

    }

    public static class FeedEntry implements BaseColumns{

        /*
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE="subtitle";
        */

        public static final String TABLE_NAME = "entry";
        public static String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE="subtitle";
    }

}

