package com.iav.id.crudwithfirebasedatabases.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iav.id.crudwithfirebasedatabases.R;
import com.iav.id.crudwithfirebasedatabases.model.DataDiriModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {


    private EditText edtNama;
    private EditText edtAlamat;
    private Button btnCreate;
    private String uid;

    private DatabaseReference databaseReference;

    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        initView(view);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = databaseReference.push().getKey();
                Log.d("", "onCreateView: " + uid);
                Toast.makeText(getActivity(), "" + uid, Toast.LENGTH_SHORT).show();
                databaseReference.child("data_diri").push().setValue(new DataDiriModel(edtNama.getText().toString().trim(), edtAlamat.getText().toString().trim()))
                        .addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Data berhasil di simpan", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }

    private void initView(View view) {
        edtNama = view.findViewById(R.id.edt_nama);
        edtAlamat = view.findViewById(R.id.edt_alamat);
        btnCreate = view.findViewById(R.id.btn_create);
    }
}
