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
import com.azoza.rcafe.model.ShowAllModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {

    private Context context;
    private List<ShowAllModel> list;


    public ShowAllAdapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View showAllView = inflater.inflate(R.layout.show_all_item, parent, false);
        return new ViewHolder(showAllView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int position2 = position;
        Glide.with(context).load(list.get(position).getImage_url()).into(holder.itemImage);
        holder.cost.setText("Rs "+String.valueOf(list.get(position).getPrice()));
        holder.name.setText(list.get(position).getName());

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detailed", list.get(position2));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView cost,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            cost = itemView.findViewById(R.id.item_cost);
            name = itemView.findViewById(R.id.item_nam);
        }
    }
}
