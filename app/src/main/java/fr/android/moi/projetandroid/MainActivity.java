package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    private String[] prenoms = new String[]{
            "Antoine", "Axel", "Cyril", "Ramzi", "Sarah", "Florian",
            "Nelly", "Sarub", "Lola", "Pichi", "Chipie", "Logan",
            "Matthieu", "Rossard", "Jacques", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        startActivity(intent); //lance le passage à l'activity Camera
    }

}
