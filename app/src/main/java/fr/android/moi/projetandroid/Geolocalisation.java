package fr.android.moi.projetandroid;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by User on 10/2/2017.
 */

public class Geolocalisation extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    Intent intent;


    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Button btnSauvegarderBattle;
    private SQLite.FeedReaderDbHelper DB;

    private String longitude;
    private String latitude;

    /**
     * Variables nécessaires pour save dans BDD EXETERNE
     */
    String id_battle;
    String team1, team2, tech1, tech2, style1, style2, art1, art2, originalite1, originalite2, espace1, espace2, total1, total2, lati, longi;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser(); // JSON parser class

    // url pour save une battle avec bdd EXTERNE
    private static final String url_insertInto_battles = "http://10.0.2.2/ProjetAndroidBddExterne/saveInBddExterne.php/";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BATLLES = "battles";
    private static final String TAG_ID = "id";
    private static final String TAG_Team1 = "team1";
    private static final String TAG_Team2 = "team2";
    private static final String TAG_TOTAL1 = "total1";
    private static final String TAG_TOTAL2 = "total2";
    private static final String TAG_TECH1 = "tech1";
    private static final String TAG_TECH2 = "tech2";
    private static final String TAG_ART1 = "art1";
    private static final String TAG_ART2 = "art2";
    private static final String TAG_ESPACE1 = "espace1";
    private static final String TAG_ESPACE2 = "espace2";
    private static final String TAG_STYLE1 = "style1";
    private static final String TAG_STYLE2 = "style2";
    private static final String TAG_ORIGINALITE1 = "originalite1";
    private static final String TAG_ORIGINALITE2 = "originalite2";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);

        intent = getIntent();
        btnSauvegarderBattle = (Button) findViewById(R.id.btnSauvegarderBattle);
        DB = new SQLite.FeedReaderDbHelper(getApplicationContext());

        // getting product details from intent
        Intent i = getIntent();

        // getting product id (pid) from intent
        team1 = i.getStringExtra(TAG_Team1);
        team2 = i.getStringExtra(TAG_Team2);
        tech1 = i.getStringExtra(TAG_TECH1);
        tech2 = i.getStringExtra(TAG_TECH2);
        art1 = i.getStringExtra(TAG_ART1);
        art2 = i.getStringExtra(TAG_ART2);
        espace1 = i.getStringExtra(TAG_ESPACE1);
        espace2 = i.getStringExtra(TAG_ESPACE2);
        style1 = i.getStringExtra(TAG_STYLE1);
        style2 = i.getStringExtra(TAG_STYLE2);
        originalite1 = i.getStringExtra(TAG_ORIGINALITE1);
        originalite2 = i.getStringExtra(TAG_ORIGINALITE2);
        total1 = i.getStringExtra(TAG_TOTAL1);
        total2 = i.getStringExtra(TAG_TOTAL2);
        lati = i.getStringExtra(TAG_LATITUDE);
        longi = i.getStringExtra(TAG_LONGITUDE);

        getLocationPermission();

        btnSauvegarderBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDansBddExterne(); //on save dans la bdd EXTERNE
                goToMatch(); //on save dans bdd INTERNE + on change d'activity
            }
        });
    }

    /*
    getLocationPermission() demande la permission d'utiliser la localisation
    FINE_LOCATION et COURSE_LOCATION
     */
    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);

        mapFragment.getMapAsync(Geolocalisation.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        if (mLocationPermissionsGranted)
        {
            //Pour avoir la localisation du téléphone
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted)
            {
                //get localisation de mon tél
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {

                    //OnComplete quand la tache est terminée
                    @Override
                    public void onComplete(@NonNull Task task) {
                        //si la tache a été accomplie avec succès
                        if (task.isSuccessful())
                        {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            latitude = String.valueOf(currentLocation.getLatitude());
                            longitude = String.valueOf(currentLocation.getLongitude());

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);


                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(Geolocalisation.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }


    //Si l'utilisateur refuse l'autorisation pour la permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode)
        {
            case LOCATION_PERMISSION_REQUEST_CODE:
                {
                if (grantResults.length > 0)
                {
                    for (int i = 0; i < grantResults.length; i++)
                    {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                        {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return; //on quitte la méthode
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    public void goToMatch() {

        /**
         * Save dans bdd INTERNE (SQLite)
         */
        Intent intent_from_Geoloc_to_Match = new Intent(this, Match.class);

        intent_from_Geoloc_to_Match.putExtra(Add.EXTRA_TEAM_NAME, intent.getStringExtra(Add.EXTRA_TEAM_NAME));
        intent_from_Geoloc_to_Match.putExtra(Add.EXTRA_TEAM_NAME_OTHER, intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER));

        // Gets the data repository in write mode
        SQLiteDatabase db = DB.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SQLite.FeedEntry.COLUMN_NAME_MY_TEAM, intent.getStringExtra(Add.EXTRA_TEAM_NAME));
        values.put(SQLite.FeedEntry.COLUMN_NAME_OTHER_TEAM, intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER));
        values.put(SQLite.FeedEntry.COLUMN_NAME_TECH_1, intent.getStringExtra(Add2.EXTRA_TECH1));
        values.put(SQLite.FeedEntry.COLUMN_NAME_ART_1, intent.getStringExtra(Add2.EXTRA_ART1));
        values.put(SQLite.FeedEntry.COLUMN_NAME_ESPACE_1, intent.getStringExtra(Add2.EXTRA_ESPACE1));
        values.put(SQLite.FeedEntry.COLUMN_NAME_STYLE_1, intent.getStringExtra(Add2.EXTRA_STYLE1));
        values.put(SQLite.FeedEntry.COLUMN_NAME_ORIGINALITE_1, intent.getStringExtra(Add2.EXTRA_ORIGINAL1));
        values.put(SQLite.FeedEntry.COLUMN_NAME_TOTAL_1, intent.getStringExtra(Add2.EXTRA_TOTAL1));
        values.put(SQLite.FeedEntry.COLUMN_NAME_TECH_2, intent.getStringExtra(Add2.EXTRA_TECH2));
        values.put(SQLite.FeedEntry.COLUMN_NAME_ART_2, intent.getStringExtra(Add2.EXTRA_ART2));
        values.put(SQLite.FeedEntry.COLUMN_NAME_ESPACE_2, intent.getStringExtra(Add2.EXTRA_ESPACE2));
        values.put(SQLite.FeedEntry.COLUMN_NAME_STYLE_2, intent.getStringExtra(Add2.EXTRA_STYLE2));
        values.put(SQLite.FeedEntry.COLUMN_NAME_ORIGINALITE_2, intent.getStringExtra(Add2.EXTRA_ORIGINAL2));
        values.put(SQLite.FeedEntry.COLUMN_NAME_TOTAL_2, intent.getStringExtra(Add2.EXTRA_TOTAL2));
        values.put(SQLite.FeedEntry.COLUMN_NAME_LATITUDE, latitude);
        values.put(SQLite.FeedEntry.COLUMN_NAME_LONGITUDE, longitude);
        values.put(SQLite.FeedEntry.COLUMN_NAME_IMAGE, intent.getByteArrayExtra(Camera.EXTRA_PHOTO)); //l'image (tab de bytes)

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SQLite.FeedEntry.TABLE_NAME, null, values);
        Toast.makeText(getApplicationContext(), "Battle added successfully!", Toast.LENGTH_SHORT).show();

        Cursor cursor = DB.getData();

        while (cursor.getCount() > 5 && cursor.getCount() != 5)
        {
            cursor = DB.deleteData();
            cursor = DB.getData();
        }

        startActivity(intent_from_Geoloc_to_Match);

    }


    public void saveDansBddExterne()
    {
        /**
         * Save dans bdd EXTERNE
         */

        new SaveProductDetails().execute();
    }

    /**
     * Background Async Task to  Save a battle
     */
    class SaveProductDetails extends AsyncTask<String, String, String> {

        // Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Geolocalisation.this);
            pDialog.setMessage("Saving the new battle ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        // Saving battles
        protected String doInBackground(String... args)
        {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_ID, id_battle));
            params.add(new BasicNameValuePair(TAG_Team1, intent.getStringExtra(Add.EXTRA_TEAM_NAME)));
            params.add(new BasicNameValuePair(TAG_Team2, intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER)));
            params.add(new BasicNameValuePair(TAG_TOTAL1, intent.getStringExtra(Add2.EXTRA_TOTAL1)));
            params.add(new BasicNameValuePair(TAG_TOTAL2, intent.getStringExtra(Add2.EXTRA_TOTAL2)));
            params.add(new BasicNameValuePair(TAG_TECH1, intent.getStringExtra(Add2.EXTRA_TECH1)));
            params.add(new BasicNameValuePair(TAG_TECH2, intent.getStringExtra(Add2.EXTRA_TECH2)));
            params.add(new BasicNameValuePair(TAG_ART1, intent.getStringExtra(Add2.EXTRA_ART1)));
            params.add(new BasicNameValuePair(TAG_ART2, intent.getStringExtra(Add2.EXTRA_ART2)));
            params.add(new BasicNameValuePair(TAG_ESPACE1, intent.getStringExtra(Add2.EXTRA_ESPACE1)));
            params.add(new BasicNameValuePair(TAG_ESPACE2, intent.getStringExtra(Add2.EXTRA_ESPACE2)));
            params.add(new BasicNameValuePair(TAG_STYLE1, intent.getStringExtra(Add2.EXTRA_STYLE1)));
            params.add(new BasicNameValuePair(TAG_STYLE2, intent.getStringExtra(Add2.EXTRA_STYLE2)));
            params.add(new BasicNameValuePair(TAG_ORIGINALITE1, intent.getStringExtra(Add2.EXTRA_ORIGINAL1)));
            params.add(new BasicNameValuePair(TAG_ORIGINALITE2, intent.getStringExtra(Add2.EXTRA_ORIGINAL2)));
            params.add(new BasicNameValuePair(TAG_LATITUDE, latitude));
            params.add(new BasicNameValuePair(TAG_LONGITUDE, longitude));


            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_insertInto_battles, "POST", params);
            if (json == null) {
                //Log.d("JSONPOST", "jsonPOST est nuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuul");
            }

            // successfully updated
            Intent i = getIntent();
            //Log.d("JSONPOST", "jsonPOST est PAAAAS nuuuuuuuuuuuuuuuuuuuul");

            // send result code 100 to notify about product update
            setResult(100, i);
            finish();


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product uupdated
            pDialog.dismiss();
        }
    }


}