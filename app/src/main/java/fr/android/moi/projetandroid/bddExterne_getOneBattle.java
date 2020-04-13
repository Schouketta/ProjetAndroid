package fr.android.moi.projetandroid;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class bddExterne_getOneBattle extends Activity {

    String id_battle;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //URL du fichier php permettant de récupérer les infos d'une battle grâce à un id
    private static final String url_oneBattle_details = "http://10.0.2.2/ProjetAndroidBddExterne/getBattleDetail.php/";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BATLLES = "battles";
    private static final String TAG_ID = "id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getting battle informations from intent
        Intent i = getIntent();

        // getting battle id from intent
        id_battle = i.getStringExtra(TAG_ID);

        // Getting complete battle details in background thread
        new GetProductDetails().execute();

    }

    /**
     * Background Async Task to Get complete product details
     */
    class GetProductDetails extends AsyncTask<String, String, String> {

        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(bddExterne_getOneBattle.this);
            pDialog.setMessage("Loading battles details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        //Getting battles details in background thread

        protected String doInBackground(String... params) {

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("id_battle", id_battle));

                        // Getting battles details by making HTTP request
                        // Note that battles details URL will use GET REQUEST
                        JSONObject json = jsonParser.makeHttpRequest(url_oneBattle_details, "GET", params);

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1)
                        {
                            // successfully received battle details
                            JSONArray productObj = json.getJSONArray(TAG_BATLLES); // JSON Array

                            // get first product object from JSON Array
                            JSONObject product = productObj.getJSONObject(0);


                        } else {
                            // battle with id not found
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }

        // After completing background task Dismiss the progress dialog
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

}