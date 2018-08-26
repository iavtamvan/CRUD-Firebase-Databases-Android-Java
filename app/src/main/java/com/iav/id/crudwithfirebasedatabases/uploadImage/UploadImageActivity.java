package com.iav.id.crudwithfirebasedatabases.uploadImage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iav.id.crudwithfirebasedatabases.R;

import java.io.IOException;
import java.util.UUID;

public class UploadImageActivity extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageRef, mountainsRef;
    StorageReference imagesRef;

    private ImageView imageView;
    private Button btnUpload;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    private Button btnDunlud;


    StorageReference link;
    private ImageView ivLihatGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        initView();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
//        imagesRef = storageRef.child("images");
//        imagesRef.getPath();
//        imagesRef.getName();
//        imagesRef.getBucket();

//        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");
//        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
//        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        btnDunlud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StorageReference httpsReference = storage.getReferenceFromUrl("ANDROID.png");
//                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("gambar/" + link);
//                StorageReference storageReference = storageRef.child("gambar/" + link);
//                Toast.makeText(UploadImageActivity.this, "Link " + link, Toast.LENGTH_SHORT).show();
//                Log.d("Storeage", "stotage: " + link);
//                // Load the image using Glide
//                Glide.with(UploadImageActivity.this)
//                        .load(filePath)
//                        .into(ivLihatGambar);

                displayImage();
            }
        });
    }

    private void uploadImage() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            String uuid = UUID.randomUUID().toString();
            StorageReference ref = storageRef.child("gambar/" + String.valueOf(System.currentTimeMillis()));
//             link = ref.getName().toString();

            link = FirebaseStorage.getInstance().getReference().child("gambar/" + uuid);
            Toast.makeText(this, "Ini link nya > > " + link, Toast.LENGTH_SHORT).show();
//            StorageReference ref = storageRef.child("gambar/"+ filePath.getPath());
            Log.d("Storage link ", "uploadImageLink: " + filePath);
//            Glide.with(UploadImageActivity.this)
//                    .using(new FirebaseImageLoader())
//                    .load(link)
//                    .into(ivLihatGambar);



            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadImageActivity.this, "Uploaded" , Toast.LENGTH_SHORT).show();
                            Glide.with(UploadImageActivity.this)
                                    .load(filePath)
                                    .into(ivLihatGambar);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadImageActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }


    private void displayImage(){
        try {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("gambar").child("1535296990375");
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(UploadImageActivity.this)
                            .load(uri)
                            .into(ivLihatGambar);
                    Toast.makeText(UploadImageActivity.this, "Sukses > " + uri.getPath(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onSuccess: " + uri.getPath());
                    Log.d("TAG", "onSuccess: " + "https://"+uri.getAuthority() + uri.getPath());
                    Log.d("TAG", "onSuccess: " + uri.getUserInfo());
                    Log.d("TAG", "onSuccess: " + uri.getEncodedPath());
                    Log.d("TAG", "onSuccess: " + uri.getHost());
                    Log.d("TAG", "onSuccess: " + uri);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UploadImageActivity.this, e.getMessage()+". Please upload your image first", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){e.printStackTrace();}
    }

    private void chooseImage() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
        btnUpload = findViewById(R.id.btn_upload);
        btnDunlud = findViewById(R.id.btn_dunlud);
        ivLihatGambar = findViewById(R.id.iv_lihat_gambar);
    }
}
