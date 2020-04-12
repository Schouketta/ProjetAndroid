package fr.android.moi.projetandroid;

import android.*;
import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);

        intent = getIntent();
        btnSauvegarderBattle = (Button) findViewById(R.id.btnSauvegarderBattle);
        DB = new SQLite.FeedReaderDbHelper(getApplicationContext());

        getLocationPermission();

        btnSauvegarderBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMatch();
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
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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

        if (mLocationPermissionsGranted) {
            //Pour avoir la localisation du téléphone
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            if (mLocationPermissionsGranted) {
                //get localisation de mon tél
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {

                    //OnComplete quand la tache est terminée
                    @Override
                    public void onComplete(@NonNull Task task) {
                        //si la tache a été accomplie avec succès
                        if (task.isSuccessful()) {
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

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
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
        Intent intent_from_Geoloc_to_Match = new Intent(this, Match.class);


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
        //values.put(SQLite.FeedEntry.COLUMN_NAME_IMAGE, intent.getByteArrayExtra(SQLite.FeedEntry.COLUMN_NAME_IMAGE));

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SQLite.FeedEntry.TABLE_NAME, null, values);
        Toast.makeText(getApplicationContext(), "Battle added successfully!", Toast.LENGTH_SHORT).show();

        Cursor cursor = DB.getData();

        while (cursor.getCount() > 5 && cursor.getCount() != 5) {
            cursor = DB.deleteData();
            cursor = DB.getData();
        }

        startActivity(intent_from_Geoloc_to_Match);

    }

}