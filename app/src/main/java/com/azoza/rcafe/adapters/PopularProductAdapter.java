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
import com.azoza.rcafe.model.PopularProductModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder>{

    private Context context;
    private List<PopularProductModel> popularProductModelList;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=   LayoutInflater.from(parent.getContext());
        View popularProductView = inflater.inflate(R.layout.popular_items, parent, false);
        return new ViewHolder(popularProductView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int position2 = position;
        Glide.with(context).load(popularProductModelList.get(position).getImage_url()).into(holder.imageView);
        holder.name.setText(popularProductModelList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductModelList.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailActivity.class);
                intent.putExtra("detailed",popularProductModelList.get(position2));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.all_img);
            name = itemView.findViewById(R.id.all_product_name);
            price = itemView.findViewById(R.id.all_price);
        }
    }
}
