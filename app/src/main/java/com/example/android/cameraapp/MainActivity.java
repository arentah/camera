package com.example.android.cameraapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    static final int PERMISSION_REQUEST_CODE = 100;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getCameraPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getCameraPermission(){
        if (!checkPermission()) {
            requestPermission();
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);

        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)){

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        } else {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Permission granted",Toast.LENGTH_SHORT).show();
                    //store permission in shared pref
                    flag = true;
                }
                else {
                    flag = false;
                    Toast.makeText(MainActivity.this,"Permission denied",Toast.LENGTH_SHORT).show();
                    //store permission in shared pref
                }
                break;
        }
    }

    public void camera(View view){

        if(flag == true) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this,"Please Grant Permission",Toast.LENGTH_LONG).show();
        }
    }



}

/*static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    TextView mImageView;
    static final int REQUEST_TAKE_PHOTO = 1;*/