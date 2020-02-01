package com.example.tej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    TextView logout,setloc;
    FirebaseAuth firebaseAuth;
Button button;
FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth =FirebaseAuth.getInstance();

        setloc = findViewById(R.id.setloc);



        logout = findViewById(R.id.button3);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

                Intent inToMain = new Intent(HomeActivity.this,loginActivity.class);
                startActivity(inToMain);
                finish();
            }
        });

        //checking for the permissions
        if(ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},8080);
        }
        button = findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intoimage = new Intent(HomeActivity.this,imageupdown.class);
                intoimage.putExtra("address",setloc.getText().toString().trim());
                startActivity(intoimage);
            }
        });
        setlocation();
    }
    public void setlocation(){
        client = (FusedLocationProviderClient)LocationServices.getFusedLocationProviderClient(this);

        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Geocoder geocoder = new Geocoder(HomeActivity.this, Locale.getDefault()) ;
                try {
                    if(location!=null){
                        List<Address> A = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        setloc.setText(A.get(0).getAddressLine(0));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
