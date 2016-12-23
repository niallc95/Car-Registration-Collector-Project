package mobileapp.assignment.mobileappproject.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import mobileapp.assignment.mobileappproject.R;

/**
 * Main activity for generating the side navigation bar and incorporating the initial content of the first screen
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS}, 1);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Setting up the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set logo for app bar
        android.support.v7.app.ActionBar appBar = getSupportActionBar();
        appBar.setDisplayHomeAsUpEnabled(false);
        appBar.setDisplayShowTitleEnabled(false);
        appBar.setCustomView(R.layout.app_bar_top);
        appBar.setDisplayShowCustomEnabled(true);

    }

    /**
     * Side drawer functions start
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Do nothing if since it is already on the home activity
        } else if (id == R.id.nav_vrt_calc) {
            startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
            return true;
        } else if (id == R.id.nav_add_registration) {
            startActivity(new Intent(MainActivity.this, AddRegActivity.class));
            return true;
        } else if (id == R.id.nav_nct_location) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
            return true;
        } else if (id == R.id.nav_email) {
            startActivity(new Intent(MainActivity.this, ContactActivity.class));
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Side drawer functions end
     */
}
