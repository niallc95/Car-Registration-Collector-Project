package mobileapp.assignment.mobileappproject.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mobileapp.assignment.mobileappproject.R;

/**
 * Contact activity which makes external calls/intents to the phone dialer and email client
 * Also uses animation.
 */

public class ContactActivity extends AppCompatActivity {
    //Declaring related UI components
    Button emailUs;
    TextView tv_phone;
    ImageView img1,img2,img3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        img1 = (ImageView) findViewById(R.id.logoView);
        img2 = (ImageView) findViewById(R.id.phoneIcon);
        img3 = (ImageView) findViewById(R.id.locateIcon);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        img1.startAnimation(myFadeInAnimation); //Set fade animation to logo icon
        img2.startAnimation(myFadeInAnimation); //Set fade animation to phone icon
        img3.startAnimation(myFadeInAnimation); //Set fade animation to location icon

        //Finding UI components
        emailUs = (Button) findViewById(R.id.sendEmail);
        tv_phone = (TextView) findViewById(R.id.phoneTxt);

        emailUs.startAnimation(myFadeInAnimation); //Set fade animation to email link
        tv_phone.startAnimation(myFadeInAnimation); //Set fade animation to call link

        //Set on click for email button
        emailUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmailFromExternalApp();
            }
        });
        //setOnClickListener for phone link
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
            //For testing purposes
            Log.i("Email sent!!", "");

        } catch (android.content.ActivityNotFoundException ex) {
            //Make a toast if there is no emailing client on the users device
            Toast.makeText(ContactActivity.this, "You have no email client installed!", Toast.LENGTH_SHORT).show();
        }
    }
}
