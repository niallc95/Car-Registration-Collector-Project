package mobileapp.assignment.mobileappproject.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobileapp.assignment.mobileappproject.Adapter.ArrayAdapter;
import mobileapp.assignment.mobileappproject.Database.MySQLiteHelper;
import mobileapp.assignment.mobileappproject.Objects.Vehicle;
import mobileapp.assignment.mobileappproject.R;

/**
 * Created by Niall on 04/12/2016.
 */

public class AddRegActivity extends AppCompatActivity{

    ListView vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_add_activity);

        //Get listView item
        vehicleList = (ListView) findViewById(R.id.listViewAddVehicles);
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

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddRegActivity.this);
                String[] modes = new String[]{"Edit vehicle information", "Delete vehicle"};
                builder.setView(vehicleList);
                final Dialog dialog = builder.create();
                dialog.show();
                vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //edit car
                        if (position == 0) {
                            Toast.makeText(AddRegActivity.this, "edit "
                                    + masterListPosition, Toast.LENGTH_SHORT).show();
                            //delete car
                        } else {
                            Toast.makeText(AddRegActivity.this, "delete "
                                    + masterListPosition, Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
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

