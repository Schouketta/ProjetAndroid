package fr.android.moi.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Camera extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "fr.android.moi.projetandroid.extra.EXTRA_PHOTO";

    Button btnPrendrePhoto;
    Button btnSuivant;
    ImageView imageViewCamera;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    Button btnOpenGallery;
    private static final int PICK_IMAGE = 100;

    Intent intent;

    Uri image_uri;
    byte[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageViewCamera = (ImageView) findViewById(R.id.imageViewCamera);
        btnPrendrePhoto = (Button) findViewById(R.id.btnPrendrePhoto);
        btnOpenGallery = (Button) findViewById(R.id.btnOpenGallery);
        btnSuivant = (Button) findViewById(R.id.btnSuivant);

        //On fait passer toutes les valeurs qu'on va vouloir save
        intent = getIntent();

        //BoutonSuivant
        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGeoloc();

            }
        });

        //Bouton click
        btnPrendrePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //si le systeme OS est >= marshmallow, request runtime pemrission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.CAMERA)==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED)
                    {
                        //Permission not enabled, request it
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //show popup to request permission
                        requestPermissions(permission,PERMISSION_CODE );

                    }
                    else{
                        //permission already
                        openCamera();

                    }
                }
                else{
                    //system os < marshmallow
                    openCamera();
                }
            }
        });

        btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery(){
        Intent gallery = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    private void openCamera(){

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //Camera intent
        Intent cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);
    }


    //Gestion permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case PERMISSION_CODE :{
                if(grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    openCamera();
                }
                else
                {
                    //permission from popup was denied
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //img = new byte[0];

        //PICk_IMAGE sert à savoir si on a bien ouvert la galerie
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            //l'image sur laquelle on clique dans la galerie devient l'image selectionnée
            image_uri=data.getData();
            imageViewCamera.setImageURI(image_uri);

            /*
            //decomposer l'img en matrice d'octets
            Bitmap b = ((BitmapDrawable)imageViewCamera.getDrawable()).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 25, bos);
            img = bos.toByteArray();*/
        }
    }

    public void goToGeoloc(){
        Intent intent_from_Camera_to_Geoloc = new Intent(this, Geolocalisation.class);
        intent_from_Camera_to_Geoloc.putExtra(Add.EXTRA_TEAM_NAME, intent.getStringExtra(Add.EXTRA_TEAM_NAME));
        intent_from_Camera_to_Geoloc.putExtra(Add.EXTRA_TEAM_NAME_OTHER, intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_TECH1, intent.getStringExtra(Add2.EXTRA_TECH1));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_TECH2, intent.getStringExtra(Add2.EXTRA_TECH2));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_TOTAL1, intent.getStringExtra(Add2.EXTRA_TOTAL1));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_TOTAL2, intent.getStringExtra(Add2.EXTRA_TOTAL2));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_ESPACE1, intent.getStringExtra(Add2.EXTRA_ESPACE1));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_ESPACE2, intent.getStringExtra(Add2.EXTRA_ESPACE2));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_ART1, intent.getStringExtra(Add2.EXTRA_ART1));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_ART2, intent.getStringExtra(Add2.EXTRA_ART2));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_ORIGINAL1, intent.getStringExtra(Add2.EXTRA_ORIGINAL1));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_ORIGINAL2, intent.getStringExtra(Add2.EXTRA_ORIGINAL2));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_STYLE1, intent.getStringExtra(Add2.EXTRA_STYLE1));
        intent_from_Camera_to_Geoloc.putExtra(Add2.EXTRA_STYLE2, intent.getStringExtra(Add2.EXTRA_STYLE2));
        //intent_from_Camera_to_Geoloc.putExtra(EXTRA_PHOTO, img);
        startActivity(intent_from_Camera_to_Geoloc);


    }
}
