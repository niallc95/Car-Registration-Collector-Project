package mobileapp.assignment.mobileappproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mobileapp.assignment.mobileappproject.Objects.Vehicle;
import mobileapp.assignment.mobileappproject.R;

/**
 * Array adapter for use when displaying vehicle data in the custom row layout
 */

public class ArrayAdapter extends android.widget.ArrayAdapter<Vehicle> {


    public ArrayAdapter(Context context, ArrayList<Vehicle> vehicles) {
        super(context, 0, vehicles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the vehicle data for this position
        Vehicle vehicle = getItem(position);

        // Inflate the view only if an existing view is not being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_row, parent, false);
        }

        // Find views
        TextView regText = (TextView) convertView.findViewById(R.id.regTxt);
        TextView ownerText = (TextView) convertView.findViewById(R.id.ownerTxt);
        ImageView icon = (ImageView) convertView.findViewById(R.id.classIV);

        // Populate the corresponding fields for each car
        regText.setText(String.valueOf(vehicle.getRegistration()));
        ownerText.setText(String.valueOf(vehicle.getOwner()));
        if(String.valueOf(vehicle.getVehicleCategory()).contains("Bike")){
            icon.setImageResource(R.drawable.ic_a_class);
        }else if(String.valueOf(vehicle.getVehicleCategory()).contains("Car")|| String.valueOf(vehicle.getVehicleCategory()).contains("Jeep")){
            icon.setImageResource(R.drawable.ic_b_class);
        }else if(String.valueOf(vehicle.getVehicleCategory()).contains("Truck")){
            icon.setImageResource(R.drawable.ic_c_class);
        }else if(String.valueOf(vehicle.getVehicleCategory()).contains("Bus")){
            icon.setImageResource(R.drawable.ic_d_class);
        }else {
            icon.setImageResource(R.drawable.logo_main);
        }

        return convertView;
    }
}
