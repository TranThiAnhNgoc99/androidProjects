package com.example.ngoctta999123.heath.Activities.Inquiry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ngoctta999123.heath.Adapters.InquiryAdapter;
import com.example.ngoctta999123.heath.Retrofit2.APIUtils;
import com.example.ngoctta999123.heath.Retrofit2.DataClient;
import com.example.ngoctta999123.heath.models.inquiries.FormTV;
import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.models.inquiries.Inquiries;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquiriesActivity extends AppCompatActivity {
    //ImageButton imbAdd;
    ListView lvDStuVan;
    ArrayList<FormTV> arrTuVan;
    InquiryAdapter adapter;
    Toolbar toolbar;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;

    final int INTENT_CREATE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuvan);

        mapping();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void mapping() {
        relativeLayout = findViewById(R.id.relativeLayoutLogin);
        linearLayout = findViewById(R.id.linearProgress);
        toolbar = findViewById(R.id.toolbarTuVan);
        lvDStuVan = findViewById(R.id.listviewDStuVan);
        arrTuVan = new ArrayList<>();
        fetchData();
    }

    private void fetchData() {
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<Inquiries> callback = dataClient.getInquiries(1, 100);
        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        callback.enqueue(new Callback<Inquiries>() {
            @Override
            public void onResponse(Call<Inquiries> call, Response<Inquiries> response) {
                Inquiries inquirie = response.body();
                if (inquirie != null) {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    arrTuVan = inquirie.getContent();
                    setAdapter();
                } else {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    Toast.makeText(InquiriesActivity.this, "Không có dữ danh sách yêu cầu tư vấn nào", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inquiries> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                Toast.makeText(InquiriesActivity.this, "Kết nối không ổn định", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        adapter = new InquiryAdapter(this, R.layout.item_listview, arrTuVan);
        lvDStuVan.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_tv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuAddTV) {
            Intent iCreate = new Intent(InquiriesActivity.this, CreateInquiriActivity.class);
            startActivityForResult(iCreate, INTENT_CREATE);
            //startActivity(new Intent(InquiriesActivity.this, CreateInquiriActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_CREATE) {
            if (resultCode == Activity.RESULT_OK) {
                boolean result = data.getBooleanExtra("result", false);
                if(result) {
                    fetchData();
                } else {
                    //Toast.makeText(this, "ccccccccccc", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
