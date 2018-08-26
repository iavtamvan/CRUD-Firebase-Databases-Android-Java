package com.iav.id.crudwithfirebasedatabases;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iav.id.crudwithfirebasedatabases.model.DataDiriModel;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtNamaEdit;
    private EditText edtAlamatEdit;
    private Button btnEdit;

    private DatabaseReference databaseReference;
    private ArrayList<DataDiriModel> dataDiriModels;

    private String idKey, nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();

        dataDiriModels = new ArrayList<>();
        Intent intent = getIntent();
        databaseReference = FirebaseDatabase.getInstance().getReference();
//        final DataDiriModel dataDiriModel  = (DataDiriModel) intent.getSerializableExtra("data");

        idKey = intent.getStringExtra("data");
        nama = intent.getStringExtra("nama");
        alamat = intent.getStringExtra("alamat");
        Toast.makeText(this, "" + idKey, Toast.LENGTH_SHORT).show();
        edtNamaEdit.setText(nama);
        edtAlamatEdit.setText(alamat);
//        if (idKey != null) {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(idKey, edtNamaEdit.getText().toString().trim(), edtAlamatEdit.getText().toString());


            }
        });
//        }
//        else {
//            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
//        }

    }

    private void updateData(String id, final String nama, final String alamat) {
        databaseReference.child("data_diri") // nama table
                .child("iav")
                .child("iav")
                .child("iav")
                .child("iav")
                .child("iav")
                .child("iav")
                .child("iav")
                .child("iav")
                .child(id) // nama bahwahnya 1
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("nama").setValue(nama);
                        dataSnapshot.getRef().child("alamat").setValue(alamat);

                        Toast.makeText(UpdateActivity.this, "Sukses Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(UpdateActivity.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void initView() {
        edtNamaEdit = findViewById(R.id.edt_nama_edit);
        edtAlamatEdit = findViewById(R.id.edt_alamat_edit);
        btnEdit = findViewById(R.id.btn_edit);
    }
}
