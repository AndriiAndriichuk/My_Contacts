package com.example.andrii.contacts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddContact extends AppCompatActivity {
    CircleImageView user_photo;
    TextInputEditText edit_name;
            EditText edit_number;
    TextInputLayout til1,til2;
    Button btn_add_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);

        user_photo = (CircleImageView) findViewById(R.id.user_photo);
        til1 = (TextInputLayout) findViewById(R.id.hint_name);
        til2 = (TextInputLayout) findViewById(R.id.hint_number);
        edit_name = (TextInputEditText) findViewById(R.id.new_name);
        edit_number = (EditText) findViewById(R.id.new_number);
        btn_add_contact = (Button) findViewById(R.id.btn_add_contact);


        user_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "New Photo" , Toast.LENGTH_SHORT).show();
            }
        });

        btn_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ContentValues cv = new ContentValues();
                cv.put(ContactsContract.Data.RAW_CONTACT_ID, 400);
                cv.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, edit_number.getText().toString());
                cv.put(ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                cv.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, edit_name.getText().toString());
                ContentResolver contentResolver = getContentResolver();
                contentResolver.insert(ContactsContract.Data.CONTENT_URI, cv);*/
                Intent intent = new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT, Uri.parse("tel:" + edit_number.getText()));
                intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true);
                startActivity(intent);
            }
        });

    }
}
