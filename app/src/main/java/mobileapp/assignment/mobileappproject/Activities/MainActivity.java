package mobileapp.assignment.mobileappproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import mobileapp.assignment.mobileappproject.Database.MySQLiteHelper;
import mobileapp.assignment.mobileappproject.Objects.Vehicle;
import mobileapp.assignment.mobileappproject.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySQLiteHelper db = new MySQLiteHelper(this);
        if(db.getAllVehicles().size()<1){
            // add Vehicles
            db.addVehicle(new Vehicle("Ferrari", "F430", "Car", "12D124599", "12/12", "C.McGregor"));
            db.addVehicle(new Vehicle("Volvo", "B8R", "Bus", "08WX3224565", "03/08", "R.O'Connor"));
            db.addVehicle(new Vehicle("Audi", "R8", "Car", "11D4599", "07/11", "S.O'Brien"));
            db.addVehicle(new Vehicle("Ferrari", "F40", "Car", "95D124", "08/95", "D.Carlyle"));
            db.addVehicle(new Vehicle("Audi", "A5", "Car", "08D237765", "08/08", "A.O'Brien"));
            db.addVehicle(new Vehicle("Opel", "Corsa", "Car", "07D2279854", "05/07", "P.Curran"));
            db.addVehicle(new Vehicle("BMW", "330i", "Car", "12D077599", "12/12", "P.Smith"));
            db.addVehicle(new Vehicle("Opel", "Vectra", "Car", "01D009898", "07/98", "D.Michaels"));
            db.addVehicle(new Vehicle("Volvo", "VN", "Truck", "02KE254554", "12/02", "M.Cullen"));
            db.addVehicle(new Vehicle("Audi", "A3", "Car", "10K237765", "09/10", "A.James"));
            db.addVehicle(new Vehicle("Mini", "Cooper", "Car", "141KK4445243", "02/14", "C.O'Keefe"));
            db.addVehicle(new Vehicle("Volkswagen", "Polo", "Car", "12D1299", "04/12", "R.O'Brien"));
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
        } else if (id == R.id.nav_registration_search) {
            startActivity(new Intent(MainActivity.this, SearchRegActivity.class));
            return true;
        } else if (id == R.id.nav_add_registration) {
            startActivity(new Intent(MainActivity.this, AddRegActivity.class));
            return true;
        } else if (id == R.id.nav_email) {

        } else if (id == R.id.nav_information) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Side drawer functions end
     */
}
