package com.example.ngoctta999123.heath.Activities.Records;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.Retrofit2.APIUtils;
import com.example.ngoctta999123.heath.Retrofit2.DataClient;
import com.example.ngoctta999123.heath.models.records.Record;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordDetailActivity extends AppCompatActivity {
    int recordId = 0;
    int type = 0;
    Toolbar toolbar;
    ProgressBar progressBar;
    TextView txtTenBS, txtTuoiBS, txtTenBN, txtTuoiBN, txtBNMoTa, txtBSTenBenh, txtBSChanDoan, txtBSTuVan, txtBSThuoc;
    TextView txtBSEmail, txtBSPhone;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        Intent intent = getIntent();
        recordId = intent.getIntExtra("id", 0);
        type = intent.getIntExtra("type", 0);

        mapping();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", true);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        fetchData();
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbarChiTienTuVan);
        txtTenBS = findViewById(R.id.txtBacSi);
        txtTuoiBS = findViewById(R.id.txtTuoiBacSi);
        txtBSPhone = findViewById(R.id.txtBSPhone);
        txtBSEmail = findViewById(R.id.txtBSEmail);
        txtTenBN = findViewById(R.id.txtTenBenhNhan);
        txtTuoiBN = findViewById(R.id.txtTuoiBenhNhan);
        txtBNMoTa = findViewById(R.id.txtBenhCanChanDoan);
        txtBSTenBenh = findViewById(R.id.txtBSTenBenh);
        txtBSChanDoan = findViewById(R.id.txtBSChanDoan);
        txtBSTuVan = findViewById(R.id.txtBSTuVan);
        txtBSThuoc = findViewById(R.id.txtBSKeDonThuoc);
        progressBar = findViewById(R.id.progressBarRecords);
        relativeLayout = findViewById(R.id.relativeLayoutLogin);
        linearLayout = findViewById(R.id.linearProgress);
    }

    private void fetchData() {
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<Record> callback = dataClient.getRecordDetail(recordId, type);
        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        callback.enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call, Response<Record> response) {
                Record record = response.body();
                if (record != null) {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    setData(record);
                } else {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    Toast.makeText(RecordDetailActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Record> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                Toast.makeText(RecordDetailActivity.this, "Kết nối không ổn định", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(Record record) {
        String tenBS = record.getDoctor().getName();
        int tuoiBS = record.getDoctor().getAge();
        String tenBN = record.getInquiry().getPatient().getName();
        int tuoiBN = record.getInquiry().getPatient().getAge();
        String content = record.getInquiry().getContent();
        String tenBenh = record.getDisease();
        String chandoan = record.getDiagnose();
        String tuvan = record.getNote();
        String donthuoc = record.getPrescription();
        String emailBS = record.getDoctor().getEmail();
        String phoneBS = record.getDoctor().getPhoneNumber();
        String avatarDoctor = (String) record.getDoctor().getAvatar();

        txtTenBS.setText(tenBS != null ? ": "+tenBS : "");
        txtTuoiBS.setText(": "+tuoiBS);
        txtTenBN.setText(tenBN != null ? ": "+ tenBN : "");
        txtTuoiBN.setText(": "+ tuoiBN);
        txtBNMoTa.setText(content != null ?  ": "+ content : "");
        txtBSTenBenh.setText(tenBenh != null ? ": "+ tenBenh : "");
        txtBSChanDoan.setText(chandoan != null ? ": "+ chandoan : "");
        txtBSTuVan.setText(tuvan != null ? ": "+ tuvan : "");
        txtBSThuoc.setText(donthuoc != null ?  ": "+ donthuoc : "");
        txtBSPhone.setText(phoneBS != null ? ": "+ phoneBS : "");
        txtBSEmail.setText(emailBS != null ? ": "+ emailBS : "");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("result", true);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
