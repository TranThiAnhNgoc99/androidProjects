package com.example.ngoctta999123.heath.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngoctta999123.heath.Activities.Profile.ProfileActivity;
import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.Retrofit2.APIUtils;
import com.example.ngoctta999123.heath.Retrofit2.DataClient;
import com.example.ngoctta999123.heath.models.Doctor;
import com.example.ngoctta999123.heath.models.User;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorActivity extends AppCompatActivity {

    TextView txtNameDoctor;
    TextView txtDoBDoctor;
    TextView txtProfessDoctor;
    TextView txtPhoneDoctor;
    TextView txtEmailDoctor;
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        AnhXa();
        fetchData();

    }

    private void AnhXa() {
        txtNameDoctor    = (TextView) findViewById(R.id.textViewNameDoctor);
        txtDoBDoctor     = (TextView) findViewById(R.id.textViewDoBDr);
        txtProfessDoctor = (TextView) findViewById(R.id.textViewProfessDr);
        txtPhoneDoctor   = (TextView) findViewById(R.id.textViewSDTBacSi);
        txtEmailDoctor   = (TextView) findViewById(R.id.textViewEmailBS);
    }
    private void fetchData() {
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<Doctor> callback = dataClient.getDoctorInfo("1");

        callback.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                Doctor doctor = response.body();
                if (doctor != null) {
//                    relativeLayout.setVisibility(View.GONE);
//                    linearLayout.setVisibility(View.GONE);
                    Toast.makeText(DoctorActivity.this, "oke", Toast.LENGTH_SHORT).show();
                    setData(doctor);
                } else {
//                    relativeLayout.setVisibility(View.GONE);
//                    linearLayout.setVisibility(View.GONE);
                   Toast.makeText(DoctorActivity.this, "Không có thông tin", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
//                relativeLayout.setVisibility(View.GONE);
//                linearLayout.setVisibility(View.GONE);
                Toast.makeText(DoctorActivity.this, "Kết nối không ổn định!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setData(Doctor doctor) {
        //userData = user;
        //userId = user.getId();
        txtNameDoctor.setText(doctor.getName() != null ? doctor.getName() : "");
        txtDoBDoctor.setText(doctor.getDateOfBirth() != null ? doctor.getDateOfBirth() : "");
        txtPhoneDoctor.setText(doctor.getPhoneNumber() != null ? doctor.getPhoneNumber() : "");
        txtEmailDoctor.setText(doctor.getEmail() != null ? doctor.getEmail() : "");
        txtProfessDoctor.setText(doctor.getRole() != null ? doctor.getRole() : "");


    }


}
