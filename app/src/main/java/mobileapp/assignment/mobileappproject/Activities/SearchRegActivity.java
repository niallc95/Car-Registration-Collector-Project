package mobileapp.assignment.mobileappproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mobileapp.assignment.mobileappproject.Adapter.ArrayAdapter;
import mobileapp.assignment.mobileappproject.Database.MySQLiteHelper;
import mobileapp.assignment.mobileappproject.Objects.Vehicle;
import mobileapp.assignment.mobileappproject.R;

/**
 * Created by Niall on 04/12/2016.
 */

public class SearchRegActivity extends AppCompatActivity{

    ListView vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_search_activity);

        //Get listView item
        vehicleList = (ListView) findViewById(R.id.listViewSearchVehicles);
        //Add divider for each listView item
        vehicleList.setDivider(null);

        MySQLiteHelper db = new MySQLiteHelper(this);
        // get all vehicles
        List<Vehicle> list = db.getAllVehicles();

        ArrayList<Vehicle> listContainer =new ArrayList<Vehicle>(list);

        // Create the adapter
        final ArrayAdapter vehicleAdapter = new ArrayAdapter(this,listContainer);

        // Attach the adapter to the studentListView
        vehicleList.setAdapter(vehicleAdapter);
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
}

