package com.azoza.rcafe.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azoza.rcafe.R;
import com.azoza.rcafe.activities.DetailActivity;
import com.azoza.rcafe.model.ProductModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ViewHolder> {

    private Context context;

    private List<ProductModel> list2;

    public NewProductsAdapter(Context context, List<ProductModel> list2) {
        this.context = context;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater=   LayoutInflater.from(parent.getContext());
        View newProductView = inflater.inflate(R.layout.new_products, parent, false);
        return new ViewHolder(newProductView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int position2 = position;
        Glide.with(context).load(list2.get(position).getImage_url()).into(holder.newImage);
        holder.newName.setText(list2.get(position).getName());
        holder.newPrice.setText(String.valueOf(list2.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detailed",list2.get(position2));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView newImage;
        TextView newName,newPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newImage = itemView.findViewById(R.id.new_img);
            newName = itemView.findViewById(R.id.new_product_name);
            newPrice = itemView.findViewById(R.id.new_price);
        }
    }
}
