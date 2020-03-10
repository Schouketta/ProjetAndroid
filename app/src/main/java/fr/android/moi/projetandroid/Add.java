package fr.android.moi.dancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {

    Button btnAdd;
    EditText myTeam, otherTeam;
    String nameTeam, nameOTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myTeam = (EditText) findViewById(R.id.myTeam);
        otherTeam = (EditText) findViewById(R.id.otherTeam);
        btnAdd = (Button) findViewById(R.id.addBattle);

        nameTeam = myTeam.getText().toString();
        nameOTeam = otherTeam.getText().toString();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btnAdd) {
                    Intent intent = new Intent(Add.this, Add2.class);
                    intent.putExtra("myTeam", nameTeam);
                    intent.putExtra("otherTeam", nameOTeam);
                    startActivity(intent);
                }
            }
        });
    }

}
