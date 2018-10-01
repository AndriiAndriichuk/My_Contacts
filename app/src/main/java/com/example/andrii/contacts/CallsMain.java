package com.example.andrii.contacts;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andrii.contacts.entity.Call;
import com.example.andrii.contacts.entity.Notification;
import com.example.andrii.contacts.entity.ResourseClass;
import com.example.andrii.contacts.entity.Sms;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CallsMain extends Fragment{

    public RecyclerView list;
    static LayoutInflater layoutInflater;
    FloatingActionButton btn_keyboard;
    ArrayList<Notification> notifications = new ArrayList<>();

    public CallsMain(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_calls, container, false);
        layoutInflater = inflater;
        btn_keyboard = (FloatingActionButton) v.findViewById(R.id.btn_keyboard);
        list = (RecyclerView) v.findViewById(R.id.list);
        list.setHasFixedSize(true);//встановлюємо фіксований розмір для ліста
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplication());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        createContactList();
        List<Notification> data = getDataList();
        TransactionAdapterCalls adapter = new TransactionAdapterCalls(data);
        list.setAdapter(adapter);
        list.invalidate();
        list.setLayoutManager(manager);

        ItemClickSupport.addTo(list)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.fromParts("tel", notifications.get(position).getContact_number(), null));
                        startActivity(callIntent);
                    }
                });


        ItemClickSupport.addTo(list)
                .setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(getActivity().getApplication(), SmsActivity.class).putExtra("name",notifications.get(position).getContact_name()).putExtra("number",notifications.get(position).getContact_number());
                        startActivity(intent);
                        return true;
                    }
                });


        btn_keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), KeyBoard.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void createContactList() {
        ContentResolver resolver = getActivity().getApplication().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //String number = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.));

                Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                String phoneNumber = "";
                if (phoneCursor.moveToFirst()) {
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                String number_temp = new String("");
                ResourseClass.contactNumbersAll.put(phoneNumber, name);
                ResourseClass.contactNumbersList.put(name,phoneNumber);
                if (phoneNumber.substring(0,3).equals("+38")){
                    number_temp = phoneNumber.substring(3, phoneNumber.length());
                    ResourseClass.contactNumbersAll.put(number_temp, name);
                }else {
                    number_temp = "+38" + phoneNumber;
                    ResourseClass.contactNumbersAll.put(number_temp, name);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private List<Notification> getDataList() {
        Cursor managedCursor = getActivity().managedQuery(CallLog.Calls.CONTENT_URI, null,null, null, null);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), (R.drawable.ic_cat_profile));
        while (managedCursor.moveToNext()) {
            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
            int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            int photo = managedCursor.getColumnIndex(CallLog.Calls.CACHED_PHOTO_URI);
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            String name1 = managedCursor.getString(name);

            Date date1 = new Date(Long.parseLong(callDate));

            /*Uri my_contact_Uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(photo));
            InputStream photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), my_contact_Uri);
            BufferedInputStream buf = new BufferedInputStream(photo_stream);
            Bitmap my_btmp = BitmapFactory.decodeStream(buf);
            image.setImageBitmap(my_btmp);*/

            String callDuration = managedCursor.getString(duration);
            String call_type = "";
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    // я дзвоню
                    call_type = "OUTGOING_TYPE";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    //мені дзвонять
                    call_type = "INCOMING_TYPE";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    //пропущено
                    call_type = "MISSED_TYPE";
                    break;
                case CallLog.Calls.REJECTED_TYPE:
                    //я збив
                    call_type = "REJECTED_TYPE";
                    break;
            }
            notifications.add(new Notification(new Call(bitmap,name1,phNumber,date1,call_type,callDuration)));
        }

        Uri message = Uri.parse("content://sms/");
        ContentResolver cr = getActivity().getContentResolver();

        String[] proj = {"*"};

        Cursor c = cr.query(message, proj, null, null, null);
        getActivity().startManagingCursor(c);
        int totalSMS = c.getCount();
        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                String number = c.getString(c
                        .getColumnIndexOrThrow("address"));
                String massage = c.getString(c.getColumnIndexOrThrow("body"));
                String isReaded = c.getString(c.getColumnIndex("read"));
                Date sms_time = new Date(Long.parseLong(c.getString(c.getColumnIndexOrThrow("date"))));
                String sms_state;
                if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                    sms_state = "inbox";
                    if (isReaded.equals("1")) sms_state = "inbox1";
                } else {
                    sms_state = "sent";
                }

                String number_temp = new String("");
                if (number.substring(0,3).equals("+38")){
                    number_temp = "+38" + number;
                }else {
                    number_temp = number.substring(3,number.length());
                }
                notifications.add(new Notification(new Sms(bitmap, ResourseClass.contactNumbersAll.get(number), number, sms_time, sms_state, massage)));
                c.moveToNext();
            }
        }

        Collections.sort(notifications);
        Collections.reverse(notifications);
        return notifications;
    }}


