package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static fr.android.moi.projetandroid.SQLite.FeedEntry.TABLE_NAME;


public class MainActivity extends AppCompatActivity {

    private SQLite.FeedReaderDbHelper DB;

    ListView mListView;
    //ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> prenoms = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SQLiteDatabase db = DB.getReadableDatabase();

        prenoms.add("Sarah");
        prenoms.add("Ella");

        /*Cursor cursor = DB.getData();
        while(cursor.moveToNext()){
            String otherTeamName = cursor.getString(2);
            list.add(otherTeamName);
        }*/

        mListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);
    }

    public void addOnClick(View view)
    {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent); //lance le passage à l'activity Add
    }

    public void historyOnClick(View view)
    {
        Intent intent = new Intent(this, History.class);
        startActivity(intent); //lance le passage à l'activity History
    }

    public void cameraOnClick(View view)
    {
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent); //lance le passage à l'activity Camera
    }

    public void statsOnClick(View view)
    {
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent); //lance le passage à l'activity Stat
    }

}
