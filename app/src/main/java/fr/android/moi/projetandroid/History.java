package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class History extends AppCompatActivity {

    ListView mListView2;
    private String[] prenoms2 = new String[]{
            "Antoine", "Axel", "Cyril", "Ramzi", "Sarah", "Florian",
            "Nelly", "Sarub", "Lola", "Pichi", "Chipie", "Logan",
            "Matthieu", "Rossard", "Jacques", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };

    //private SQLite.FeedReaderDbHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        /*SQLiteDatabase db = DB.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                SQLite.FeedEntry.COLUMN_NAME_OTHER_TEAM
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = "";
        String[] selectionArgs = {};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                SQLite.FeedEntry.COLUMN_NAME_OTHER_TEAM + " DESC";

        Cursor cursor = db.query(
                SQLite.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        Log.d("cursor", cursor.getString(0));*/


        mListView2 = (ListView) findViewById(R.id.listView2);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(History.this,
                android.R.layout.simple_list_item_1, prenoms2);
        mListView2.setAdapter(adapter);
    }
}
