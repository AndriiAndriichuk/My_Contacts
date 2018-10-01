package com.example.andrii.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class KeyBoard extends AppCompatActivity implements View.OnClickListener{

    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0,btn_star,btn_sharp;
    Button btn_del,btn_last,btn_hide;
    ImageButton btn_call;
    EditText edit_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyboard_layout);

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_star = (Button) findViewById(R.id.btn_star);
        btn_sharp = (Button) findViewById(R.id.btn_sharp);

        btn_del = (Button) findViewById(R.id.btn_del);
        btn_last = (Button) findViewById(R.id.btn_last);
        btn_hide = (Button) findViewById(R.id.btn_hide);
        btn_call = (ImageButton) findViewById(R.id.btn_call);

        edit_number = (EditText) findViewById(R.id.edit_number);


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_star.setOnClickListener(this);
        btn_sharp.setOnClickListener(this);

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
            }
        });

        btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_2:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_3:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_4:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_5:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_6:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_7:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_8:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_9:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_0:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_star:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sharp:
                Toast.makeText(KeyBoard.this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
