package com.example.ngoctta999123.heath.Activities.Records;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.ngoctta999123.heath.Adapters.RecordAdapter;
import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.Retrofit2.APIUtils;
import com.example.ngoctta999123.heath.Retrofit2.DataClient;
import com.example.ngoctta999123.heath.models.records.Record;
import com.example.ngoctta999123.heath.models.records.Records;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordsActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    ListView listViewRecords;
    ArrayList<Record> arrRecords;
    RecordAdapter recordAdapter;
    ProgressBar progressBar;
    RadioButton rdSucKhoe;
    RadioButton rdDinhDuong;
    Toolbar toolbar;
    RadioGroup rdGroup;
    int checkType = 0;
    String page = "1";
    String size = "1000";

    final int INTENT_RECORD_DETAIL = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tuvan);

        mapping();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        fetchData();

        event();
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbarRecordList);
        relativeLayout = findViewById(R.id.relativeLayoutLogin);
        linearLayout = findViewById(R.id.linearProgress);
        progressBar = findViewById(R.id.progressBarRecords);
        rdGroup = findViewById(R.id.rdGroup);
        rdSucKhoe = findViewById(R.id.rdSucKhoe);
        rdDinhDuong = findViewById(R.id.rdDinhDuong);
        listViewRecords = findViewById(R.id.lvRecords);
        arrRecords = new ArrayList<>();
    }

    private void event() {
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdSucKhoe:
                        checkType = 0;
                        fetchData();
                        break;
                    case R.id.rdDinhDuong:
                        checkType = 1;
                        fetchData();
                        break;
                }
            }
        });
    }

    private void fetchData() {
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<Records> callback = dataClient.getRecords(page, size, checkType);
        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        callback.enqueue(new Callback<Records>() {
            @Override
            public void onResponse(Call<Records> call, Response<Records> response) {
                Records records = response.body();
                if (records != null) {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    arrRecords = records.getContent();
                    setAdapter();
                } else {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    Toast.makeText(RecordsActivity.this, "Danh sách tư vấn rỗng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Records> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                Toast.makeText(RecordsActivity.this, "Kết nối không ổn định", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        recordAdapter = new RecordAdapter(this, R.layout.item_record, arrRecords);
        listViewRecords.setAdapter(recordAdapter);
        listViewRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RecordsActivity.this, RecordDetailActivity.class);
                intent.putExtra("id", arrRecords.get(i).getId());
                intent.putExtra("type", checkType);
                //startActivity(intent);
                startActivityForResult(intent, INTENT_RECORD_DETAIL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
