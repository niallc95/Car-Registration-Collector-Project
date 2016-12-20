package mobileapp.assignment.mobileappproject.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
    FloatingActionButton add;
    final Context context = this;
    String reg, manufacturer, type, model, owner;
    List<Vehicle> list;
    EditText searchBar;
    ArrayAdapter vehicleAdapter;
    ArrayList<Vehicle> listContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);

        final MySQLiteHelper db = new MySQLiteHelper(this);

        //Set search bar edit text
        searchBar = (EditText) findViewById(R.id.txtsearch);
        //Get listView item
        vehicleList = (ListView) findViewById(R.id.listViewAddVehicles);
        //Add divider for each listView item
        vehicleList.setDivider(null);

        add = (FloatingActionButton) findViewById(R.id.add);

        initList();

        // add button listener
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(AddRegActivity.this);
                dialog.setContentView(R.layout.dialog_add);
                dialog.setTitle("Title");

                Button commit = (Button) dialog.findViewById(R.id.custom_button_commit);
                Button cancel = (Button) dialog.findViewById(R.id.custom_button_cancel);

                commit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        EditText registrationTxt = (EditText) dialog.findViewById(R.id.RegistrationET);
                        EditText modelTxt = (EditText) dialog.findViewById(R.id.ModelET);
                        EditText ownerTxt = (EditText) dialog.findViewById(R.id.OwnerET);
                        Spinner typeSpinner = (Spinner) dialog.findViewById(R.id.type_spinner);
                        Spinner manufacturerSpinner = (Spinner) dialog.findViewById(R.id.manufacturers_spinner);
                        TextView typeView = (TextView) typeSpinner.getSelectedView();
                        type = typeView.getText().toString();
                        TextView manufacturerView = (TextView) manufacturerSpinner.getSelectedView();
                        manufacturer = manufacturerView.getText().toString();

                        if (registrationTxt.length() != 0 && modelTxt.length() != 0 && ownerTxt.length() != 0) {
                            reg = registrationTxt.getText().toString();
                            model = modelTxt.getText().toString();
                            owner = ownerTxt.getText().toString();
                            db.addVehicle(new Vehicle(reg, manufacturer, type, model, owner));
                            dialog.dismiss();

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Please fill in all of the required information", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }


                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        //Search functionality
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    // reset listview
                    initList();
                } else {
                    // perform search
                    searchItem(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
            public void searchItem(String textToSearch) {
                for (Vehicle vehicle : listContainer) {
                    if (db.getVehicle(textToSearch) == null) {
                        Log.d("getVehicle", String.valueOf(db.getVehicle(textToSearch)));
                        listContainer.remove(vehicle);
                    }
                    vehicleAdapter.notifyDataSetChanged();
                }
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

    public void initList(){
        final MySQLiteHelper db = new MySQLiteHelper(this);
        // get all vehicles
        list = db.getAllVehicles();
        listContainer = new ArrayList<>(list);
        // Create the adapter
        vehicleAdapter = new ArrayAdapter(this, listContainer);
        // Attach the adapter to the studentListView
        vehicleList.setAdapter(vehicleAdapter);

        if(db.getAllVehicles().size()<12){
            // add Vehicles
            db.addVehicle(new Vehicle("Ferrari", "F430", "Car", "12D124599", "C.McGregor"));
            db.addVehicle(new Vehicle("Volvo", "B8R", "Bus", "08WX3224565", "R.O'Connor"));
            db.addVehicle(new Vehicle("Audi", "R8", "Car", "11D4599", "S.O'Brien"));
            db.addVehicle(new Vehicle("Ferrari", "F40", "Car", "95D124", "D.Carlyle"));
            db.addVehicle(new Vehicle("Audi", "A5", "Car", "08D237765", "A.O'Brien"));
            db.addVehicle(new Vehicle("Opel", "Corsa", "Car", "07D2279854", "P.Curran"));
            db.addVehicle(new Vehicle("BMW", "330i", "Car", "12D077599", "P.Smith"));
            db.addVehicle(new Vehicle("Opel", "Vectra", "Car", "01D009898", "D.Michaels"));
            db.addVehicle(new Vehicle("Volvo", "VN", "Truck", "02KE254554", "M.Cullen"));
            db.addVehicle(new Vehicle("Audi", "A3", "Car", "10K237765", "A.James"));
            db.addVehicle(new Vehicle("Mini", "Cooper", "Car", "141KK4445243", "C.O'Keefe"));
            db.addVehicle(new Vehicle("Volkswagen", "Polo", "Car", "12D1299", "R.O'Brien"));
        }

    }
  }

