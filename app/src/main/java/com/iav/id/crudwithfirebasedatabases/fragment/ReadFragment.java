package com.iav.id.crudwithfirebasedatabases.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iav.id.crudwithfirebasedatabases.R;
import com.iav.id.crudwithfirebasedatabases.adapter.DataDiriAdapter;
import com.iav.id.crudwithfirebasedatabases.model.DataDiriModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {
    private RecyclerView rv;

    private DatabaseReference databaseReference;
    private ArrayList<DataDiriModel> dataDiriModels;
    private DataDiriAdapter dataDiriAdapter ;

    public ReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        initView(view);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        dataDiriModels = new ArrayList<>();


        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference.child("data_diri").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    DataDiriModel dataDiriModel = snapshot.getValue(DataDiriModel.class);
                    dataDiriModel.setKey(dataSnapshot.getKey());
                    dataDiriModels.add(dataDiriModel);
                }
                dataDiriAdapter = new DataDiriAdapter(dataDiriModels, getActivity());
                rv.setAdapter(dataDiriAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rv);
    }
}
