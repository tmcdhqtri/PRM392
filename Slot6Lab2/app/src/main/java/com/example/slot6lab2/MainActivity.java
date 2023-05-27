package com.example.slot6lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // Use GridLayoutManager with 2 columns

        // Create dummy product data
        productList = new ArrayList<>();
        productList.add(new Product("Product 1", "Description 1", R.drawable.product1));
        productList.add(new Product("Product 2", "Description 2", R.drawable.product2));
        productList.add(new Product("Product 3", "Description 3", R.drawable.product3));
        productList.add(new Product("Product 4", "Description 4", R.drawable.product4));
        productList.add(new Product("Product 5", "Description 5", R.drawable.product5));
        productList.add(new Product("Product 6", "Description 6", R.drawable.product6));
        productList.add(new Product("Product 7", "Description 7", R.drawable.product7));
        productList.add(new Product("Product 8", "Description 8", R.drawable.product8));
        productList.add(new Product("Product 9", "Description 9", R.drawable.product9));


        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        private List<Product> productList;

        public ProductAdapter(List<Product> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Product product = productList.get(position);
            holder.textViewTitle.setText(product.getTitle());
            holder.imageViewProduct.setImageResource(product.getImageResourceId());
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView textViewTitle;
            ImageView imageViewProduct;

            public ProductViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product product = productList.get(position);
                    Toast.makeText(v.getContext(), "Clicked on " + product.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class Product {
        private String title;
        private String description;
        private int imageResourceId;

        public Product(String title, String description, int imageResourceId) {
            this.title = title;
            this.description = description;
            this.imageResourceId = imageResourceId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }
    }
}
