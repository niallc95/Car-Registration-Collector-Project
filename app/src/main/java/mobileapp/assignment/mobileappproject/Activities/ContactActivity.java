package mobileapp.assignment.mobileappproject.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mobileapp.assignment.mobileappproject.R;

/**
 * Created by Niall on 12/12/2016.
 */

public class ContactActivity extends AppCompatActivity {
    Button emailUs;
    TextView tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        emailUs = (Button) findViewById(R.id.sendEmail);
        tv_phone = (TextView) findViewById(R.id.phoneTxt);
        //Set on click for email button
        emailUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmailFromExternalApp();
            }
        });

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String phone_no = tv_phone.getText().toString().replaceAll("-", "");
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone_no));
                startActivity(callIntent);
            }
        });
    }

    protected void sendEmailFromExternalApp() {
        Log.i("Send email", "");
        //recipient
        String[] TO = {getString(R.string.hoarder_email)};
        String[] CC = {""};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

            Log.i("Email sent!!", "");

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactActivity.this, "You have no email client installed!", Toast.LENGTH_SHORT).show();
        }
    }
}
