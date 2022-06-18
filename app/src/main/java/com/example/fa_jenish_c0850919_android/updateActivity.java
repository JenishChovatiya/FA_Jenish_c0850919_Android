package com.example.fa_jenish_c0850919_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class updateActivity extends AppCompatActivity implements OnMapReadyCallback {

    EditText placeNameUP;
    Button updateBtn,deleteBtn;


    String id1,name1;


    GoogleMap gMap;
    private Marker userLocationMarker;
    private Marker destinationMarker;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 1;


    LocationManager locationManager;
    LocationListener locationListener;

    List<Marker> markerList1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapUpdatePage);
        if (mapFragment != null) {
            mapFragment.getMapAsync(updateActivity.this);
        }




        placeNameUP = findViewById(R.id.placeETUdatePage);
        updateBtn = findViewById(R.id.updateBtnUpdatePage);
        deleteBtn = findViewById(R.id.deleteBtnUpdatePagte);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null)
        {

            ab.setTitle(name1);

        }

        //setting up on Click listener
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(placeNameUP.getText().toString().isEmpty())
                {
                    placeNameUP.setError("Please Provide the name.");
                }
                else
                {

                    DatabaseHelper myDBHelper = new DatabaseHelper(updateActivity.this);

                    name1 = placeNameUP.getText().toString().trim();

                    myDBHelper.updateData(id1, name1);
                    finish();
                }

            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });



    }


    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name"))
        {
            //getting the intent data from one page to another
            id1 = getIntent().getStringExtra("id");
            name1 = getIntent().getStringExtra("name");


            //Setting the data from intent
            placeNameUP.setText(name1);



        }
        else
        {
            Toast.makeText(this, "No data Available.", Toast.LENGTH_SHORT).show();
        }
    }



    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name1 + " ?");
        builder.setMessage("Are you sure you want to delete " + name1 + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(updateActivity.this);
                myDB.deleteData(id1);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        gMap = googleMap;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                setHomeMarker(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (!hasLocationPermission())
            requestLocationPermission();
        else
            startUpdateLocation();
    }


    private void setHomeMarker(Location location)
    {
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions options = new MarkerOptions().position(userLocation)
                .title("You're Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        userLocationMarker = gMap.addMarker(options);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10));
    }


    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    private boolean hasLocationPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void startUpdateLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (REQUEST_CODE == requestCode) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
            }
        }
    }
}