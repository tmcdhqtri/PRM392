package com.example.slot11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slot11_2.Room.ProductDAO;
import com.example.slot11_2.Room.ProductDatabase;
import com.example.slot11_2.Room.Products;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameEd, descriptionEd, priceEd;
    private Button update;
    private Products products;
    private ProductDatabase productDatabase;
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        productDatabase = ProductDatabase.getInstance(this);
        productDAO = productDatabase.getDAO();

        nameEd = findViewById(R.id.name);
        descriptionEd = findViewById(R.id.description);
        priceEd = findViewById(R.id.price);
        update = findViewById(R.id.update);

        products = (Products) getIntent().getSerializableExtra("model");

        nameEd.setText(products.getName());
        descriptionEd.setText(products.getDescription());
        priceEd.setText(String.valueOf(products.getPrice()));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = nameEd.getText().toString();
                String newDescription = descriptionEd.getText().toString();
                double newPrice = Double.parseDouble(priceEd.getText().toString());

                Products productModel = new Products(products.getId(), newName, newDescription, newPrice);
                productDAO.update(productModel);
                Toast.makeText(UpdateActivity.this, "Updated Product ID: " + productModel.getId(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
