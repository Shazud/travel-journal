package com.ddp.tj.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ddp.tj.R;
import com.ddp.tj.database.AppDatabase;
import com.ddp.tj.fragment.AboutUsFragment;
import com.ddp.tj.fragment.ContactFragment;
import com.ddp.tj.fragment.HomeFragment;
import com.ddp.tj.trip.Trip;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ArrayList<Trip> data;
    private ArrayList<Trip> dataUnchanged;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production").build();
        readData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        //Setup the action bar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Initialize the navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set the default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(data)).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Check for the pressed menu option
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(data)).commit();
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutUsFragment()).commit();
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        //Close the drawer afterwards
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    public void returnToPreviousFragment(){
        if(getSupportFragmentManager().getBackStackEntryCount()!=0) {
            getSupportFragmentManager().popBackStack();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(data)).commit();
        }
    }

    public void changeCurrentFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    private void readData() {
        /*
        data.add(new Trip("Travel around the glove", "Hanoi", null, 1499.99, 4.65, false));
        data.add(new Trip("Go home gi", "USA", null, 123, 0, false));
        data.add(new Trip("Take out Viet Cong", "Vietnam", null, 3400, 3.21, false));
        data.add(new Trip("Lol lmao", "Hanoi", null, 1499.99, 2.5, false));
        data.add(new Trip("Soaskdokasd", "aksdoaskdokaoskd", null, 1499.99, 3, false));
        data.add(new Trip("Sad lamba", "Hugga Bugga", null, 1499.99, 4.5, false));
        data.add(new Trip("Go to the moon", "Moon moon", null, 2000000, 4, true));
        data.add(new Trip("Go to mars", "Planet Mars", null, 1000000000, 5, true));
        */
        Runnable readData = new Runnable() {
            @Override
            public void run() {
                data = new ArrayList<Trip>(db.tripDao().getAllTrips());
                for(Trip trip:data){
                    try{
                        File file = getFileStreamPath("image_" + trip.getTripID() + ".png");
                        FileInputStream fis = new FileInputStream(file);
                        Bitmap bitmap = BitmapFactory.decodeStream(fis);
                        trip.setPicture(bitmap);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread dataThread = new Thread(readData);
        dataThread.start();
        //TODO: make nice loading animation
        try {
            dataThread.join();

        }
        catch (Exception ignore){

        }
        dataUnchanged = new ArrayList<Trip>();
        dataUnchanged.clear();
        dataUnchanged.addAll(data);

    }



    public AppDatabase getDb(){
        return db;
    }
}
