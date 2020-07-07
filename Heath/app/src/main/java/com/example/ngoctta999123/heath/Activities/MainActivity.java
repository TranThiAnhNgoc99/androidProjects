package com.example.ngoctta999123.heath.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ngoctta999123.heath.Activities.Inquiry.InquiriesActivity;
import com.example.ngoctta999123.heath.Activities.Profile.ProfileActivity;
import com.example.ngoctta999123.heath.Activities.Records.RecordsActivity;
import com.example.ngoctta999123.heath.models.Item;
import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.Adapters.MainAddapter;
import com.example.ngoctta999123.heath.models.phien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    phien Phien;
    GridView grvMain;
    ArrayList<Item> arrItem;
    MainAddapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        //implementation 'com.android.support:appcompat-v7:28.0.0'
        adapter = new MainAddapter(this, R.layout.item_gridview, arrItem);
        grvMain.setAdapter(adapter);
        grvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;

                switch (position) {
                    case 0:

                        intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        break;

                    case 1:

                        intent = new Intent(MainActivity.this, InquiriesActivity.class);
                        startActivity(intent);
                        break;


                    case 2:
                        intent = new Intent(MainActivity.this, DoctorActivity.class);
                        startActivity(intent);
                        break;


                    case 3:
                        intent = new Intent(MainActivity.this, RecordsActivity.class);
                        startActivity(intent);
                        break;


                }

            }
        });
    }

    private void AnhXa() {
        grvMain = (GridView) findViewById(R.id.gridViewMain);
        arrItem = new ArrayList<>();

        arrItem.add(new Item(R.string.profile, R.drawable.ic_profile));
        arrItem.add(new Item(R.string.apply_for_advice, R.drawable.ic_homthuoc));
        arrItem.add(new Item(R.string.doctor, R.mipmap.ic_doctor));
        arrItem.add(new Item(R.string.advice_rep, R.drawable.ic_tuvan));
    }
}
