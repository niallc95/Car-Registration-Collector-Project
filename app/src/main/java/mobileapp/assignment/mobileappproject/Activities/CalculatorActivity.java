package mobileapp.assignment.mobileappproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mobileapp.assignment.mobileappproject.R;

/**
 * Created by Niall on 04/12/2016.
 */

public class CalculatorActivity extends AppCompatActivity{

    double rrp;
    double emissions;
    double calculation;
    double percentage = 0.36;
    Button calculate;
    EditText costTxt;
    EditText emissionsTxt;
    EditText yearTxt;
    EditText manufacturerTxt;
    EditText modelTxt;
    TextView VRT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);

        calculate = (Button) findViewById(R.id.calculateBtn);
        costTxt = (EditText)findViewById(R.id.costEditTxt);
        emissionsTxt = (EditText)findViewById(R.id.emissionsEditTxt);
        manufacturerTxt = (EditText)findViewById(R.id.manufacturerEditTxt);
        modelTxt = (EditText)findViewById(R.id.modelEditTxt);
        yearTxt = (EditText)findViewById(R.id.yearEditTxt);
        VRT = (TextView) findViewById(R.id.calculationTxt);

        // add calculate button click listener
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(costTxt.length() == 0||emissionsTxt.length() == 0||manufacturerTxt.length() == 0||modelTxt.length() == 0||yearTxt.length() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please fill in all of the required information", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    rrp = Double.valueOf(costTxt.getText().toString());
                    emissions = Double.valueOf(emissionsTxt.getText().toString());
                    calculation = rrp * percentage;
                    VRT.setText("€" + String.valueOf(calculation));
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
}
