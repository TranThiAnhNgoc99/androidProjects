package com.example.ngoctta999123.heath.Activities.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngoctta999123.heath.Activities.SplashScreen;
import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.Retrofit2.APIUtils;
import com.example.ngoctta999123.heath.Retrofit2.DataClient;
import com.example.ngoctta999123.heath.models.Image;
import com.example.ngoctta999123.heath.models.profile.UpdateInfo;
import com.example.ngoctta999123.heath.models.User;
import com.example.ngoctta999123.heath.models.phien;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    phien Phien;
    Button btnLogout, btnSaved, btnChonAnh;
    EditText edtPhoneUser, edtAddressUser, edtFullName;
    TextView edtDateOfBirth, edtEmailUser;
    CircleImageView imgAvatar;
    int Request_Code_Image = 123;
    RadioButton rdName, rdNu;
    int userId;
    int gender;
    int genderValidate;
    String dateOfBirth = "";
    User userData;
    private List<Uri> mUris = new ArrayList<>();
    private static final int REQUEST_WRITE_PERMISSION = 786;

    private final String AVATAR_IMAGE = "AVATAR_IMAGE";
    private final String AVATAR = "AVATAR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mapping();

        fetchData();

        eventClick();

    }

    private void eventClick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSharePreference("TOKEN");
                deleteSharePreference(AVATAR_IMAGE);
                Intent intent = new Intent(ProfileActivity.this, SplashScreen.class);
                startActivity(intent);
            }
        });

        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });

        btnSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdName.isChecked()) {
                    gender = 1;
                } else if (rdNu.isChecked()) {
                    gender = 0;
                }
                boolean isEnable = isEnableSaveButton(userData);
                if (!isEnable) {
                    Toast.makeText(ProfileActivity.this, "Vui lòng chỉnh sửa ít nhất một thông tin cá nhân", Toast.LENGTH_LONG).show();
                } else {
                    String name = edtFullName.getText().toString();
                    String email = userData.getEmail();
                    String phone = edtPhoneUser.getText().toString();
                    String address = edtAddressUser.getText().toString();
                    String day = edtDateOfBirth.getText().toString();

                    Log.d("cuong", day);

                    UpdateInfo updateInfo = new UpdateInfo(address, name, day, gender, phone, email);

                    DataClient dataClient = APIUtils.getData(getApplicationContext());
                    Call<User> callback = dataClient.updateInfo(updateInfo);
                    relativeLayout.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    callback.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            if (user != null) {
                                relativeLayout.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.GONE);
                                Toast.makeText(ProfileActivity.this, "Chỉnh sửa thông tin thành công", Toast.LENGTH_LONG).show();
                            } else {
                                relativeLayout.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.GONE);
                                Toast.makeText(ProfileActivity.this, "Format ngày tháng không hợp lệ", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            relativeLayout.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.GONE);
                            Toast.makeText(ProfileActivity.this, "Kết nối không ổn định!", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

        edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateOfBirth();
            }
        });
    }

    private void selectDateOfBirth() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, day, month, year);
        datePickerDialog.show();
    }

    private void fetchData() {
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<User> callback = dataClient.getUserInfo();
        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        callback.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null) {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    setData(user);
                } else {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, "Không có thông tin", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, "Kết nối không ổn định!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setData(User user) {
        userData = user;
        userId = user.getId();
        dateOfBirth = user.getDateOfBirth();
        edtFullName.setText(user.getName() != null ? user.getName() : "");
        edtDateOfBirth.setText(user.getDateOfBirth() != null ? user.getDateOfBirth() : "");
        edtPhoneUser.setText(user.getPhoneNumber() != null ? user.getPhoneNumber() : "");
        edtEmailUser.setText(user.getEmail() != null ? user.getEmail() : "");
        edtAddressUser.setText(user.getAddress() != null ? user.getAddress() : "");
        if (user.getGender() == 0) {
            rdNu.setChecked(true);
            genderValidate = 0;
        } else {
            rdName.setChecked(true);
            genderValidate = 1;
        }
        onShowAvatar();
    }

    private void onShowAvatar() {
        SharedPreferences sharedPreferences = getSharedPreferences(AVATAR_IMAGE, Context.MODE_PRIVATE);
        String avartar = sharedPreferences.getString(AVATAR, "");
        Log.d("bin", avartar);
        if (avartar.isEmpty() || avartar == null) {
            imgAvatar.setImageResource(R.drawable.ic_profile);
        } else {
            LoadImage loadImage = new LoadImage(imgAvatar);
            loadImage.execute(avartar);
        }
    }

    private boolean isEnableSaveButton(User user) {
        boolean isEnable = false;
        if (!edtFullName.getText().toString().equals(user.getName())) {
            isEnable = true;
        }
        if (!edtDateOfBirth.getText().toString().equals(user.getDateOfBirth())) {
            isEnable = true;
        }
        if (!edtEmailUser.getText().toString().equals(user.getEmail())) {
            isEnable = true;
        }
        String address = edtAddressUser.getText().toString().isEmpty() ? null : edtAddressUser.getText().toString();
        if (address != user.getAddress()) {
            isEnable = true;
        }
        if (rdName.isChecked()) {
            gender = 1;
        } else if (rdNu.isChecked()) {
            gender = 0;
        }
        if (gender != genderValidate) {
            isEnable = true;
        }
        return isEnable;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Request_Code_Image && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mUris.add(uri);
                imgAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            uploadFiles(mUris);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadFiles(List<Uri> lists) {
        if (mUris.isEmpty()) {
            Toast.makeText(this, "Please select some image", Toast.LENGTH_SHORT).show();
            return;
        }
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            MultipartBody.Part multipartBody = prepareFilePart("files", lists.get(i));
            parts.add(multipartBody);
        }
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<String> call = dataClient.uploadAvatar(parts);
        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null) {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, "Upload hình thành công", Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences = getSharedPreferences(AVATAR_IMAGE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(AVATAR, response.body());
                    editor.apply();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, "Kết nối không ổn định", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getContentResolver().query(contentURI, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int idx = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            result = cursor.getString(idx);
        }
        cursor.close();
        return result;
    }

    private void mapping() {
        btnLogout = findViewById(R.id.buttonLogout);
        btnSaved = findViewById(R.id.buttonSave);
        btnChonAnh = findViewById(R.id.buttonImgUser);
        edtAddressUser = findViewById(R.id.editTextAddressUser);
        edtEmailUser = findViewById(R.id.editTextEmailUser);
        edtPhoneUser = findViewById(R.id.editTextPhoneUser);
        edtFullName = findViewById(R.id.edtFullName);
        edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        imgAvatar = findViewById(R.id.imageViewAvatar);
        rdName = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
        relativeLayout = findViewById(R.id.relativeLayoutLogin);
        linearLayout = findViewById(R.id.linearProgress);
    }

    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));
        Log.d("hung", file.toString());
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(fileUri)),
                        file
                );
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, Request_Code_Image);
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, Request_Code_Image);
        }
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView imageView1) {
            this.imageView = imageView1;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgAvatar.setImageBitmap(bitmap);
        }
    }

    private void deleteSharePreference(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

