package com.iav.id.crudwithfirebasedatabases.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iav.id.crudwithfirebasedatabases.R;
import com.iav.id.crudwithfirebasedatabases.UpdateActivity;
import com.iav.id.crudwithfirebasedatabases.model.DataDiriModel;

import java.util.ArrayList;

public class DataDiriAdapter extends RecyclerView.Adapter<DataDiriAdapter.ViewHolder> {
    private ArrayList<DataDiriModel> dataDiriModels;
    private Context context;
    private DatabaseReference databaseReference;
    private DataDiriAdapter dataDiriAdapter;
    public DataDiriAdapter(ArrayList<DataDiriModel> dataDiriModels, Context context) {
        this.dataDiriModels = dataDiriModels;
        this.context = context;
//        this.listener = (FirebaseDBReadActivity)
    }



    @Override
    public DataDiriAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        dataDiriAdapter = new DataDiriAdapter(dataDiriModels, context);
        dataDiriAdapter.notifyDataSetChanged();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DataDiriAdapter.ViewHolder holder, final int position) {
        holder.tvName.setText(dataDiriModels.get(position).getNama());
        holder.tvAlamat.setText(dataDiriModels.get(position).getAlamat());
        holder.cvklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("data", dataDiriModels.get(position).getIdKey());
                intent.putExtra("nama", holder.tvName.getText().toString().trim());
                intent.putExtra("alamat", holder.tvAlamat.getText().toString().trim());
                context.startActivity(intent);
            }
        });
        holder.cvklik.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "" + dataDiriModels.get(position).getIdKey(), Toast.LENGTH_SHORT).show();
                databaseReference.child("data_diri")
                        .child("iav")
                        .child("iav")
                        .child("iav")
                        .child("iav")
                        .child("iav")
                        .child("iav")
                        .child("iav")
                        .child("iav")
                        .child(dataDiriModels.get(position).getIdKey())
                        .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Sukses deleted" , Toast.LENGTH_SHORT).show();
                    }
                });
//                final Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.dialog_rfit);
//                dialog.setTitle("Pilih Aksi");
//                Button btnEdit = dialog.findViewById(R.id.btn_edit);
//                final EditText edtEditNama = dialog.findViewById(R.id.edt_nama_edit);
//                final EditText edtEditAlamat = dialog.findViewById(R.id.edt_alamat_edit);
//
//                btnEdit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                dialog.show();


                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataDiriModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAlamat;
        CardView cvklik;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_list_name);
            tvAlamat = itemView.findViewById(R.id.tv_list_alamat);
            cvklik = itemView.findViewById(R.id.cvklik);

        }
    }

    public interface FirebaseDataListener{
        void onDeleteData(DataDiriModel dataDiriModel, int position);
    }
}
