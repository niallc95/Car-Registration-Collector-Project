package mobileapp.assignment.mobileappproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mobileapp.assignment.mobileappproject.Objects.Vehicle;
import mobileapp.assignment.mobileappproject.R;


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

        // Populate the corresponding fields for each car
        regText.setText(String.valueOf(vehicle.getRegistration()));
        ownerText.setText(String.valueOf(vehicle.getOwner()));

        return convertView;
    }
}
