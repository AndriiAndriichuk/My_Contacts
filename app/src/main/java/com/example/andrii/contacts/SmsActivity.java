package com.example.andrii.contacts;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {
    String Sent_Sms = "SENT_SMS";
    String Deliver_Sms = "DELIVER_SMS";

    Intent sent_intent = new Intent(Sent_Sms);
    Intent deliver_intent = new Intent(Deliver_Sms);

    PendingIntent sent_pi,deliver_pi;
    TextInputEditText edit_name,edit_number,edit_sms;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        edit_name = (TextInputEditText) findViewById(R.id.sent_name);
        edit_number = (TextInputEditText) findViewById(R.id.sent_number);
        edit_sms = (TextInputEditText) findViewById(R.id.sent_text);

        sent_pi = PendingIntent.getBroadcast(SmsActivity.this,0,sent_intent,0);
        deliver_pi = PendingIntent.getBroadcast(SmsActivity.this,0,deliver_intent,0);
        btn_send = (Button) findViewById(R.id.btn_send);


        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String name = b.getString("name");
        final String number = b.getString("number");
        edit_name.setText(name);
        edit_number.setText(number);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number, null, edit_sms.getText().toString(), null, null);
                Toast.makeText(SmsActivity.this,"Надіслано до " + name,Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
