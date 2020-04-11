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
                String danseurs = separated[0].trim(); // this will contain "Fruit"
                String score = separated[1].trim(); // this will contain " they taste good"

                String[] separatedDanseurs = danseurs.split("vs");
                String myTeamName = separatedDanseurs[0].trim(); // this will contain "Fruit"
                String otherTeamName = separatedDanseurs[1].trim(); // this will contain " they taste good"

                String[] separatedScore = score.split("\\-");
                String total = separatedScore[0].trim(); // this will contain "Fruit"
                String total2 = separatedScore[1].trim(); // this will contain " they taste good"

                String[] separatedTotal1 = total.split(":");
                total = separatedScore[0].trim(); // this will contain "Fruit"
                String total1 = separatedScore[1].trim(); // this will contain " they taste good"
                /*Log.d("message", message);
                Log.d("danseurs", danseurs);
                Log.d("score", score);
                Log.d("myTeamName", myTeamName);
                Log.d("otherTeamName", otherTeamName);
                Log.d("total1", total1);
                Log.d("total2", total2);*/

                Intent intent = new Intent(MainActivity.this, Match.class);
                intent.putExtra(Add.EXTRA_TEAM_NAME, myTeamName);
                intent.putExtra(Add.EXTRA_TEAM_NAME_OTHER, otherTeamName);
                //intent.putExtra("total1", total1);
                //intent.putExtra("total2", total2);
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
