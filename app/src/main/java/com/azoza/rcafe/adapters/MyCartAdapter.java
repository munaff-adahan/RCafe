package com.azoza.rcafe.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azoza.rcafe.R;
import com.azoza.rcafe.model.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> list;

    int totalAmount = 0;

    public MyCartAdapter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=   LayoutInflater.from(parent.getContext());
        View myCartItem = inflater.inflate(R.layout.my_cart_item, parent, false);
        return new ViewHolder(myCartItem);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(list.get(position).getCurrentDate());
        holder.time.setText(list.get(position).getCurrentTime());
        holder.name.setText(list.get(position).getProductName());
        holder.pPrice.setText(list.get(position).getProductPrice());
        holder.tPrice.setText(String.valueOf(list.get(position).getTotalPrice())+" Lkr");
        holder.qty.setText(list.get(position).getTotalQuantity());

        //Calculating total Amount
        totalAmount = totalAmount + list.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date,time,name,pPrice,tPrice,qty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            name = itemView.findViewById(R.id.product_name);
            pPrice = itemView.findViewById(R.id.product_price);
            tPrice = itemView.findViewById(R.id.total_price);
            qty = itemView.findViewById(R.id.total_quantity);
        }
    }
}
