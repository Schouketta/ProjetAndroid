package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;


public class MainActivity extends AppCompatActivity {

    private SQLite.FeedReaderDbHelper DB;

    ListView mListView;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new SQLite.FeedReaderDbHelper(getApplicationContext());

        SQLiteDatabase db = DB.getReadableDatabase();

        Cursor cursor = DB.getData();

        while(cursor.moveToNext()){
            String myTeamName = cursor.getString(1);
            String otherTeamName = cursor.getString(2);
            String total1 = cursor.getString(8);
            String total2 = cursor.getString(14);

            String message = myTeamName + " vs " + otherTeamName + "\n| Score : " + total1 + " - " + total2;
            list.add(message);
        }

        mListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String message = adapterView.getItemAtPosition(i).toString();

                String[] separated = message.split("\\|");
                String danseurs = separated[0].trim();
                String score = separated[1].trim();

                String[] separatedDanseurs = danseurs.split("vs");
                String myTeamName = separatedDanseurs[0].trim();
                String otherTeamName = separatedDanseurs[1].trim();

                String[] separatedScore = score.split("\\-");
                String total = separatedScore[0].trim();
                String total2 = separatedScore[1].trim();

                String[] separatedTotal1 = total.split(":");
                total = separatedScore[0].trim();
                String total1 = separatedScore[1].trim();

                Intent intent = new Intent(MainActivity.this, Match.class);
                intent.putExtra(Add.EXTRA_TEAM_NAME, myTeamName);
                intent.putExtra(Add.EXTRA_TEAM_NAME_OTHER, otherTeamName);
                startActivity(intent); //lance le passage à l'activity Match
            }
        });
    }



    public void addOnClick(View view)
    {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent); //lance le passage à l'activity Add
    }

    public void historyOnClick(View view)
    {
        Intent intent = new Intent(this, bddExterne.class);
        startActivity(intent); //lance le passage à l'activity History
    }

    public void cameraOnClick(View view)
    {
        Intent intent = new Intent(this, CameraMainActivity.class);
        startActivity(intent); //lance le passage à l'activity Camera
    }

    public void statsOnClick(View view)
    {
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent); //lance le passage à l'activity Stat
    }

}
