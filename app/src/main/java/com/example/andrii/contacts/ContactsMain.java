package com.example.andrii.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.andrii.contacts.entity.Contact;
import com.example.andrii.contacts.entity.ResourseClass;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsMain extends Fragment {
    public RecyclerView list;
    ImageView image;
    FloatingActionButton btn_add_contact;
    static LayoutInflater layoutInflater;
    List<Contact> list_con = new ArrayList<>();
    AutoCompleteTextView search_contacts;


    public ContactsMain() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_contacts, container, false);
        layoutInflater = inflater;
        list = (RecyclerView) v.findViewById(R.id.list);

        image = (CircleImageView) v.findViewById(R.id.user_icon);

        search_contacts = (AutoCompleteTextView) v.findViewById(R.id.search_contacts);

        btn_add_contact = (FloatingActionButton) v.findViewById(R.id.btn_add_contact);

        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(search_contacts.getWindowToken(), 0);
        list.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplication());

        List<Contact> data = getDataList();
        TransactionAdapterContacts adapter = new TransactionAdapterContacts(data);
        list.setAdapter(adapter);
        list.invalidate();
        list.setLayoutManager(manager);

        ItemClickSupport.addTo(list)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.fromParts("tel", list_con.get(position).getNumber(), null));
                        startActivity(callIntent);
                    }
                });

        ItemClickSupport.addTo(list)
                .setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(getActivity().getApplication(), SmsActivity.class).putExtra("name",list_con.get(position).getName()).putExtra("number",list_con.get(position).getNumber());
                        startActivity(intent);
                        return true;
                    }
                });



        btn_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), AddContact.class);
                startActivity(intent);
            }
        });

        return v;
    }


    public LayoutInflater getInflater(){
        return ContactsMain.layoutInflater;
    }

    private List<Contact> getDataList() {
       HashSet<String> alContacts = new HashSet<>();
        ArrayList<String> numbers = new ArrayList<>();
        ContentResolver resolver = getActivity().getApplication().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //String number = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.));

                Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                String phoneNumber = "";
                while (phoneCursor.moveToNext()) {
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                alContacts.add(name);
            }
        }catch (Exception e){

        }
        cursor.close();
        ArrayList<String> sortedList = new ArrayList(alContacts);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), (R.drawable.ic_cat_profile));
        for (String el : sortedList) {
            list_con.add(new Contact(bitmap,el, ResourseClass.contactNumbersList.get(el)));
        }
        return list_con;

    }
}





