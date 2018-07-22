package com.numa.emma.numa.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;

public class Main extends AppCompatActivity {
    private Controller controller;
    private FragmentManager fm;
    private BottomNavigationView navigation;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fm = getSupportFragmentManager();
            for (int i=0; i<fm.getBackStackEntryCount(); i++)
                fm.popBackStack();
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.calendar:
                    fragment = new Calendar();
                    break;
                case R.id.programma:
                    fragment = new Program();
                    break;
                case R.id.profile:
                    fragment = new Profile();
                    break;
                default:
                    fragment = new Profile();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        verifyStoragePermissions(this);

        controller = (Controller) getApplicationContext();
        navigation = findViewById(R.id.navigation);

        Intent intent = new Intent(this, Intro.class);
        startActivity(intent);

        Fragment fragment;
        if (controller.load()) {
            fragment = new Program();
            navigation.setSelectedItemId(R.id.programma);
        } else {
            controller = new Controller();
            fragment = new Profile();
            navigation.setSelectedItemId(R.id.profile);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setIcon(R.drawable.icona);
            builder.setMessage("Sicuro di voler uscire?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else
            super.onBackPressed();
    }
}