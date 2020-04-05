package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

import static java.sql.Types.NULL;

public class Add2 extends AppCompatActivity {

    Button btnAdd;
    TextView team1, team2, total1, total2;
    Spinner tech1, art1, espace1, style1, original1;
    String Stech1, Sart1, Sespace1, Sstyle1, Soriginal1;
    Spinner tech2, art2, espace2, style2, original2;
    String Stech2, Sart2, Sespace2, Sstyle2, Soriginal2;

    public static SQLiteHelper sqLiteHelper;

    /*public void myClickHandler(View view) {

        /*String tag = view.getTag().toString();

        switch (tag) {
            case "tech1":
            case "art1":
            case "espace1":
            case "style1":
            case "original1":
                total = tech1.getSelectedItem().toString();
                break;

            default:
                total1.setText(total);
                break;
        }

        total = (int) tech1.getSelectedItem() + (int) art1.getSelectedItem() + (int) espace1.getSelectedItem() + (int) style1.getSelectedItem() + (int) original1.getSelectedItem() ;
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        btnAdd = (Button) findViewById(R.id.addBattle);
        team1 = (TextView) findViewById(R.id.team1);
        team2 = (TextView) findViewById(R.id.team2);

        total1 = (TextView) findViewById(R.id.total1);
        tech1 = (Spinner) findViewById(R.id.tech1);
        art1 = (Spinner) findViewById(R.id.art1);
        espace1 = (Spinner) findViewById(R.id.espace1);
        style1 = (Spinner) findViewById(R.id.style1);
        original1 = (Spinner) findViewById(R.id.original1);

        total2 = (TextView) findViewById(R.id.total2);
        tech2 = (Spinner) findViewById(R.id.tech2);
        art2 = (Spinner) findViewById(R.id.art2);
        espace2 = (Spinner) findViewById(R.id.espace2);
        style2 = (Spinner) findViewById(R.id.style2);
        original2 = (Spinner) findViewById(R.id.original2);


        Intent intent = getIntent();
        final String nameTeam1 = intent.getStringExtra(Add.EXTRA_TEAM_NAME);
        final String nameTeam2 = intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER);
        Toast.makeText(this, nameTeam1, Toast.LENGTH_SHORT).show();

        team1.setText(nameTeam1);
        team2.setText(nameTeam2);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Stech1 = tech1.getSelectedItem().toString();
                Sart1 = art1.getSelectedItem().toString();
                Sespace1 = espace1.getSelectedItem().toString();
                Sstyle1 = style1.getSelectedItem().toString();
                Soriginal1 = original1.getSelectedItem().toString();

                int cal1 = Integer.parseInt(Stech1) + Integer.parseInt(Sart1) + Integer.parseInt(Sespace1) + Integer.parseInt(Sstyle1) + Integer.parseInt(Soriginal1);
                String Scal1 = Integer.toString(cal1);
                Log.d("total1", Scal1);


                Stech2 = tech2.getSelectedItem().toString();
                Sart2 = art2.getSelectedItem().toString();
                Sespace2 = espace2.getSelectedItem().toString();
                Sstyle2 = style2.getSelectedItem().toString();
                Soriginal2 = original2.getSelectedItem().toString();

                int cal2 = Integer.parseInt(Stech2) + Integer.parseInt(Sart2) + Integer.parseInt(Sespace2) + Integer.parseInt(Sstyle2) + Integer.parseInt(Soriginal2);
                String Scal2 = Integer.toString(cal2);
                Log.d("total2", Scal2);

                try {
                    sqLiteHelper.insertData(
                            nameTeam1.trim(),
                            nameTeam2.trim(),
                            nameTeam2.trim(),
                            Stech1.trim(),
                            Sart1.trim(),
                            Sespace1.trim(),
                            Sstyle1.trim(),
                            Soriginal1.trim(),
                            Scal1.trim(),
                            Stech2.trim(),
                            Sart2.trim(),
                            Sespace2.trim(),
                            Sstyle2.trim(),
                            Soriginal2.trim(),
                            Scal2.trim()
                    );
                    Toast.makeText(getApplicationContext(), "Added Succesfully!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Add2.this, Match.class);
                startActivity(intent);

            }
        });

        sqLiteHelper = new SQLiteHelper(this, "battleDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS BATTLE (Id INTEGER PRIMARY KEY AUTOINCREMENT, myTeamName VARCHAR, otherTeamName VARCHAR, nbRounds VARCHAR," +
                "tech1 VARCHAR, art1 VARCHAR, espace1 VARCHAR, style1 VARCHAR, originalite1 VARCHAR, total1 VARCHAR," +
                "tech2 VARCHAR, art2 VARCHAR, espace2 VARCHAR, style2 VARCHAR, originalite2 VARCHAR, total2 VARCHAR)");
    }
}

