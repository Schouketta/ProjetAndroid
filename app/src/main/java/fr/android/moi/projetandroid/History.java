package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

   /* private SQLite.FeedReaderDbHelper DB;
    ListView myListViewHistory;
    ArrayList<String> list2 = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        myListViewHistory = (ListView) findViewById(R.id.list);

        ArrayAdapter<ListViewHistory_Adapter> adapter = new ArrayAdapter<ListViewHistory_Adapter>(this, android.R.layout.simple_list_item_1 );
        adapter.add( new ListViewHistory_Adapter("Antoinou", "Chat", "5", "7"));
        adapter.add( new ListViewHistory_Adapter("Sarah", "Hamster", "5", "7"));
        myListViewHistory.setAdapter(adapter);

    }*/
}
