package com.example.ngoctta999123.heath.Activities.Inquiry;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ngoctta999123.heath.Adapters.ImageAdapter;
import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.Retrofit2.APIUtils;
import com.example.ngoctta999123.heath.Retrofit2.DataClient;
import com.example.ngoctta999123.heath.models.Image;
import com.example.ngoctta999123.heath.models.inquiries.CreateInquiriRequest;
import com.example.ngoctta999123.heath.models.records.Inquiry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateInquiriActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout2;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    RadioGroup rdGType;
    EditText edtMoTa;
    Button imgAddHinh;
    GridView grvHinh;
    Button btnSubmit;
    LinearLayout linearLayout;
    RadioButton rdHealth, rdDD;
    Toolbar toolbar;

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int REQUEST_WRITE_PERMISSION = 1000;
    int Request_Code_Image = 999;

    ImageAdapter arrayAdapter;
    List<Bitmap> bitmapList = new ArrayList<>();
    List<Uri> images = new ArrayList<>();

    String albumID = "";
    String dateCurrent = "";
    String content = "";
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formtv);

        mapping();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", false);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = isValid();
                if (!isValid) {
                    Toast.makeText(CreateInquiriActivity.this, "Vui lòng nhập đầy đủ thông tin để tạo yêu cầu tư vấn", Toast.LENGTH_SHORT).show();
                } else {
                    createInquiry();
                }
            }
        });

        imgAddHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private boolean isValid() {
        boolean isValid = false;
        content = edtMoTa.getText().toString().trim();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateCurrent = df.format(calendar.getTime());

        if (rdHealth.isChecked()) {
            type = 0;
        } else {
            type = 1;
        }

        if (content.isEmpty() ||/* albumID.isEmpty() ||*/ dateCurrent.isEmpty()) {
            isValid = false;
        } else {
            isValid = true;
        }

       // Log.d("hung", dateCurrent + "-contemnt" + content + "-type" + type + "ab" + albumID );
        return isValid;
    }

    private void createInquiry() {
        CreateInquiriRequest createInquiriRequest = new CreateInquiriRequest(albumID, content, dateCurrent, type);
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<Inquiry> callback = dataClient.createInquiries(createInquiriRequest);
        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout2.setVisibility(View.VISIBLE);
        callback.enqueue(new Callback<Inquiry>() {
            @Override
            public void onResponse(Call<Inquiry> call, Response<Inquiry> response) {
                if (response.body() != null) {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    Toast.makeText(CreateInquiriActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("result", true);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                } else {
                    relativeLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    Toast.makeText(CreateInquiriActivity.this, "Lỗi tạo tư vấn", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inquiry> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                Toast.makeText(CreateInquiriActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("result", true);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void selectImage() {

        final CharSequence[] items = {"Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateInquiriActivity.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    //requestPermissionCamera();
                } else if (items[i].equals("Gallery")) {
                    requestPermission();

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                bitmapList.add(bmp);
                arrayAdapter.notifyDataSetChanged();

            } else if (requestCode == SELECT_FILE) {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmapList.add(bitmap);
                    images.add(uri);
                    arrayAdapter.notifyDataSetChanged();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (images.size() == 1) {
                    //uploadFiles();
                    Toast.makeText(this, "Bạn vui lòng chọn đủ hai hình ảnh", Toast.LENGTH_SHORT).show();
                }
                if (images.size() == 2) {
                    linearLayout.setVisibility(View.GONE);
                    uploadFiles();
                }
            }
        }
    }

    //upload file trả về album id -> tạo tư vấn
    private void uploadFiles() {
        if (images.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
            return;
        }
        relativeLayout.setVisibility(View.VISIBLE);
        linearLayout2.setVisibility(View.VISIBLE);
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            MultipartBody.Part multipartBody = prepareFilePart("files", images.get(i));
            parts.add(multipartBody);
        }
        DataClient dataClient = APIUtils.getData(getApplicationContext());
        Call<Image> call = dataClient.uploadPhoto(parts);
        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, retrofit2.Response<Image> response) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                if (response != null) {
                    if (response.body().getAlbumId() != null) {
                        albumID = response.body().getAlbumId();
                        Toast.makeText(CreateInquiriActivity.this, "Upload hình thành công", Toast.LENGTH_SHORT).show();
                        Log.d("cuong2", response.body().getAlbumId());
                    } else {
                        Toast.makeText(CreateInquiriActivity.this, "Không xác định", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                Log.d("cuong3", t.toString());
                Toast.makeText(CreateInquiriActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //trả về đường dẫn chính xác của tấm hình trong điện thoại
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

    //tạo 1 Multipart Body trong đó có file và filename
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
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, SELECT_FILE);
        }
    }

    //xin quyền truy cập bộ nhớ để lấy hình ảnh từ galery
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, SELECT_FILE);
        }
    }

    private void mapping() {
        relativeLayout = findViewById(R.id.relativeLayoutLogin);
        linearLayout2 = findViewById(R.id.linearProgress);
        rdGType = findViewById(R.id.radioGroupType);
        edtMoTa = findViewById(R.id.editTextDescription);
        imgAddHinh = findViewById(R.id.buttonAddAnh);
        grvHinh = findViewById(R.id.gridViewHinhAnh);
        btnSubmit = findViewById(R.id.buttonSubmit);
        linearLayout = findViewById(R.id.linearChonHinh);
        rdHealth = findViewById(R.id.radioButtonhealth);
        rdDD = findViewById(R.id.radioButtonNutri);
        toolbar = findViewById(R.id.toolBarTaoTuVan);

        arrayAdapter = new ImageAdapter(CreateInquiriActivity.this, R.layout.image_item_activity, bitmapList);
        grvHinh.setAdapter(arrayAdapter);
    }

    //xin quyền camera của user -> hiện popup
  /*  private void requestPermissionCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }*/

    //nếu người dùng cho phép thì dc chụp hình , k thì show thông báo
    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("result", false);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
