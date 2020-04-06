package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Match extends AppCompatActivity {

    Button btnMap;
    private SQLite.FeedReaderDbHelper DB;
    TextView score, winner, teams;
    TextView team1, tech1, art1, espace1, style1, original1, cal1;
    TextView team2, tech2, art2, espace2, style2, original2, cal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        btnMap = (Button) findViewById(R.id.btnMap);
        score = (TextView) findViewById(R.id.score);
        winner = (TextView) findViewById(R.id.winner);
        teams = (TextView) findViewById(R.id.teams);

        team1 = (TextView) findViewById(R.id.myTeamName);
        team2 = (TextView) findViewById(R.id.otherTeamName);

        cal1 = (TextView) findViewById(R.id.mtotal1);
        tech1 = (TextView) findViewById(R.id.mtech1);
        art1 = (TextView) findViewById(R.id.mart1);
        espace1 = (TextView) findViewById(R.id.mespace1);
        style1 = (TextView) findViewById(R.id.mstyle1);
        original1 = (TextView) findViewById(R.id.moriginal1);

        cal2 = (TextView) findViewById(R.id.mtotal2);
        tech2 = (TextView) findViewById(R.id.mtech2);
        art2 = (TextView) findViewById(R.id.mart2);
        espace2 = (TextView) findViewById(R.id.mespace2);
        style2 = (TextView) findViewById(R.id.mstyle2);
        original2 = (TextView) findViewById(R.id.moriginal2);


        Intent intent = getIntent();
        final String myTeamName = intent.getStringExtra("myTeamName");
        final String otherTeamName = intent.getStringExtra("otherTeamName");
        final String total1 = intent.getStringExtra("total1");
        final String total2 = intent.getStringExtra("total2");
        Toast.makeText(this, myTeamName, Toast.LENGTH_SHORT).show();


        DB = new SQLite.FeedReaderDbHelper(getApplicationContext());

        SQLiteDatabase db = DB.getReadableDatabase();

        Cursor cursor = DB.getDataForMatch(myTeamName, otherTeamName);

        while(cursor.moveToNext()){

            Log.d("myteam", cursor.getString(1));
            Log.d("otherteam", cursor.getString(2));
            Log.d("tech1", cursor.getString(3));
            Log.d("total2", cursor.getString(14));

            team1.setText(cursor.getString(1));
            team2.setText(cursor.getString(2));
            tech1.setText(cursor.getString(3));
            art1.setText(cursor.getString(4));
            espace1.setText(cursor.getString(5));
            style1.setText(cursor.getString(6));
            original1.setText(cursor.getString(7));
            cal1.setText(cursor.getString(8));
            tech2.setText(cursor.getString(9));
            art2.setText(cursor.getString(10));
            espace2.setText(cursor.getString(11));
            style2.setText(cursor.getString(12));
            original2.setText(cursor.getString(13));
            cal2.setText(cursor.getString(14));

            String Sscore = cursor.getString(8) + " - " + cursor.getString(14);
            score.setText(Sscore);
            String Steams = cursor.getString(1) + " vs " + cursor.getString(2);
            teams.setText(Steams);

            if(Integer.parseInt(cursor.getString(8)) > Integer.parseInt(cursor.getString(14)) ){
                winner.setText(cursor.getString(1));
            }
            else if(Integer.parseInt(cursor.getString(14)) > Integer.parseInt(cursor.getString(8))){
                winner.setText(cursor.getString(2));
            }
            else if(Integer.parseInt(cursor.getString(14)) == Integer.parseInt(cursor.getString(8))){
                winner.setText("EX AEQUO");
            }

        }




        final Intent intent2 = new Intent(this, Geolocalisation.class);
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent2);
            }
        });


    }
}
