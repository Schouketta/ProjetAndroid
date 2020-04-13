package fr.android.moi.projetandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class bddExterne extends ListActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> battlesList;

    // URL pour récupérer la liste de toutes les battles
    private static final String url_battle_details = "http://10.0.2.2/ProjetAndroidBddExterne/getAllBattles.php/";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BATTLES = "battles";
    private static final String TAG_ID = "id";
    private static final String TAG_Team1= "team1";
    private static final String TAG_Team2= "team2";
    private static final String TAG_TOTAL1= "total1";
    private static final String TAG_TOTAL2= "total2";
    private static final String TAG_LATITUDE= "latitude";
    private static final String TAG_LONGITUDE= "longitude";

    // products JSONArray
    JSONArray battlesJson = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Hashmap for ListView
        battlesList = new ArrayList<HashMap<String, String>>();

        // Loading battles in Background Thread
        new LoadAllProducts().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String id_battle = ((TextView) view.findViewById(R.id.list_id)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),bddExterne_getOneBattle.class);
                // sending pid to next activity
                in.putExtra(TAG_ID, id_battle);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });

    }


    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           pDialog = new ProgressDialog(bddExterne.this);
            pDialog.setMessage("Loading all battles. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        // getting All battles from url
         protected String doInBackground(String... args)
         {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_battle_details, "GET", params);
            if(json == null)
            {
                Log.d("JSONSARAH", "json est nul");
            }


            try {
                // Checking for SUCCESS TAG

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                Log.d("JSONbddExterne", "json PB TRY");

                battlesJson = json.getJSONArray(TAG_BATTLES);
                Log.d("JSONbddExterne3", "json PB TRY333");


                // looping through All Products
                    for (int i = 0; i < battlesJson.length(); i++) {
                        JSONObject c = battlesJson.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_ID);
                        String team1 = c.getString(TAG_Team1);
                        String team2 = c.getString(TAG_Team2);
                        String total1 = c.getString(TAG_TOTAL1);
                        String total2 = c.getString(TAG_TOTAL2);
                        String latitude = c.getString(TAG_LATITUDE);
                        String longitude = c.getString(TAG_LONGITUDE);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, id);
                        map.put(TAG_Team1, team1);
                        map.put(TAG_Team2, team2);
                        map.put(TAG_TOTAL1, total1);
                        map.put(TAG_TOTAL2, total2);
                        map.put(TAG_LATITUDE, latitude);
                        map.put(TAG_LONGITUDE, longitude);

                        // adding HashList to ArrayList
                        battlesList.add(map);
                   }
                } else {
                    // no battles found
                    Log.d("NoBattleFound", "No battles found");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        // After completing background task Dismiss the progress dialog
        // On affiche dans notre ListView History toutes les battles, grâce à list_item_history
        protected void onPostExecute(String file_url)
        {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(bddExterne.this, battlesList, R.layout.list_item_history,
                            new String[] { TAG_ID, TAG_Team1, TAG_Team2, TAG_TOTAL1, TAG_TOTAL2, TAG_LATITUDE, TAG_LONGITUDE},
                            new int[] { R.id.list_id, R.id.list_team1, R.id.list_team2, R.id.list_score1, R.id.list_score2, R.id.list_latitude, R.id.list_longitude});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}





































