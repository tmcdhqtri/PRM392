package com.example.slot11_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slot11_2.Room.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    private List<Products> productsList;

    private AdapterListener adapterListener;

    public ProductAdapter(Context context, AdapterListener listener) {
        this.context = context;
        productsList = new ArrayList<>();
        this.adapterListener = listener;
    }

    public void addProduct(Products products) {
        productsList.add(products);
        notifyDataSetChanged();
    }

    public void removeProduct(int position) {
        productsList.remove(position);
        notifyDataSetChanged();
    }

    public void clearData() {
        productsList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.product_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Products products = productsList.get(position);
        holder.name.setText("Name: "+ products.getName());
        holder.description.setText("Description: " + products.getDescription());
        holder.price.setText("Price: " + String.valueOf(products.getPrice())+"$");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnDelete(products.getId(), position);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnUpdate(products);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description, price;
        private ImageView delete, update;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
        }
    }
}
