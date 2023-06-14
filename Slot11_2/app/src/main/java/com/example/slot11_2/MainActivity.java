package com.example.slot11_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slot11_2.Room.ProductDAO;
import com.example.slot11_2.Room.ProductDatabase;
import com.example.slot11_2.Room.Products;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {
    EditText nameEd, descriptionEd, priceEd;
    Button insertBtn;
    RecyclerView myRecycler;
    private ProductDatabase productDatabase;
    private ProductDAO productDAO;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productDatabase = ProductDatabase.getInstance(this);
        productDAO = productDatabase.getDAO();

        nameEd = findViewById(R.id.name);
        descriptionEd = findViewById(R.id.description);
        priceEd = findViewById(R.id.price);
        insertBtn = findViewById(R.id.insert);
        myRecycler = findViewById(R.id.usersRecycler);

        productAdapter = new ProductAdapter(this, this);
        myRecycler.setAdapter(productAdapter);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEd.getText().toString().trim();
                String description = descriptionEd.getText().toString().trim();
                String priceString = priceEd.getText().toString().trim();

                if (name.isEmpty() || description.isEmpty() || priceString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please input all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double price = Double.parseDouble(priceString);
                Products products = new Products(0, name, description, price);
                long insertedId = productDAO.insert(products);
                products.setId((int) insertedId);
                productAdapter.addProduct(products);
                nameEd.setText("");
                descriptionEd.setText("");
                priceEd.setText("");
                Toast.makeText(MainActivity.this, "Inserted ID: " + insertedId, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void fetchData() {
        productAdapter.clearData();
        List<Products> productsList = productDAO.getAllProduct();
        for (int i = 0; i < productsList.size(); i++) {
            Products products = productsList.get(i);
            productAdapter.addProduct(products);
        }
    }

    @Override
    public void OnUpdate(Products products) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("model", products);
        startActivity(intent);
    }

    @Override
    public void OnDelete(int id, int pos) {
        productDAO.delete(id);
        productAdapter.removeProduct(pos);
        Toast.makeText(this, "Deleted Product ID: " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
