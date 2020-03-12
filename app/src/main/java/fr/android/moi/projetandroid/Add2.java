package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Add2 extends AppCompatActivity {

    Button btnAdd;
    TextView team1;
    TextView team2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        team1 = (TextView) findViewById(R.id.team1);
        team2 = (TextView) findViewById(R.id.team2);
        btnAdd = (Button) findViewById(R.id.addBattle);


        Intent intent = getIntent();
        String nameTeam1 = intent.getStringExtra(Add.EXTRA_TEAM_NAME);
        String nameTeam2 = intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER);
        Toast.makeText(this, nameTeam1, Toast.LENGTH_SHORT).show();

        team1.setText(nameTeam1);
        team2.setText(nameTeam2);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Add2.this, Match.class);
                startActivity(intent);

            }
        });
    }
}
