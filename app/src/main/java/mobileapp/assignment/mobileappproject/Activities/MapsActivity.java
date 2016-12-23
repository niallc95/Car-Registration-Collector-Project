package mobileapp.assignment.mobileappproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mobileapp.assignment.mobileappproject.R;
/**
 * Maps screen for google maps integration when displaying the NCT locations
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng northpoint = new LatLng(53.410314, -6.268044);
        LatLng greenHills = new LatLng(53.296674, -6.350300);
        LatLng arklow = new LatLng(52.788706, -6.174188);
        LatLng galway = new LatLng(53.318361, -8.219306);
        LatLng dundalk = new LatLng(54.004092, -6.377908);
        LatLng kells = new LatLng(53.726003, -6.879147);


        //Add markers
        mMap.addMarker(new MarkerOptions().position(northpoint).title("Northpoint 2 (Exit 4, M50) NCT Centre"));
        mMap.addMarker(new MarkerOptions().position(greenHills).title("Greenhills (Exit 11,M50) NCT Centre"));
        mMap.addMarker(new MarkerOptions().position(arklow).title("Arklow NCT Centre"));
        mMap.addMarker(new MarkerOptions().position(galway).title("Ballinasloe NCT Centre"));
        mMap.addMarker(new MarkerOptions().position(dundalk).title("Dundalk NCT Centre"));
        mMap.addMarker(new MarkerOptions().position(kells).title("Kells NCT Centre"));
        //Set initial focus of map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(northpoint));
    }
}
