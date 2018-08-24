package com.iav.id.crudwithfirebasedatabases.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iav.id.crudwithfirebasedatabases.R;
import com.iav.id.crudwithfirebasedatabases.model.DataDiriModel;

import java.util.ArrayList;

public class DataDiriAdapter extends RecyclerView.Adapter<DataDiriAdapter.ViewHolder> {
    private ArrayList<DataDiriModel> dataDiriModels;
    private Context context;

    public DataDiriAdapter(ArrayList<DataDiriModel> dataDiriModels, Context context) {
        this.dataDiriModels = dataDiriModels;
        this.context = context;
    }



    @Override
    public DataDiriAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataDiriAdapter.ViewHolder holder, int position) {
        tvName.setText(dataDiriModels.get(position).getNama());
        tvAlamat.setText(dataDiriModels.get(position).getAlamat());

        cvklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + tvName.getText().toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataDiriModels.size();
    }

    TextView tvName, tvAlamat;
    CardView cvklik;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_list_name);
            tvAlamat = itemView.findViewById(R.id.tv_list_alamat);
            cvklik = itemView.findViewById(R.id.cvklik);

        }
    }
}
