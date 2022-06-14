package com.example.lab2_amandeep_c0850554_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class createProducts extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_products);
        sqLiteDatabase = openOrCreateDatabase("employee_db",MODE_PRIVATE,null);
    }
    public void addProducts(View view){
        TextView Product_Name = findViewById(R.id.Enter_name);
        TextView Product_Description = findViewById(R.id.Enter_Description);
        TextView Product_Price = findViewById(R.id.Enter_price);
        String name = Product_Name.getText().toString().trim();
        String description = Product_Description.getText().toString().trim();
        String price = Product_Price.getText().toString().trim();

        if(name.isEmpty()){
            Product_Name.setError("Name Field Require Data");
            Product_Name.requestFocus();
            return;
        }
        if(description.isEmpty()){
            Product_Description.setError("Description Field Require Data");
            Product_Description.requestFocus();
            return;
        }
        if(price.isEmpty()){
            Product_Price.setError("Price Field Require Data");
            Product_Price.requestFocus();
            return;
        }
        String sql = "INSERT INTO products(name,description,price)"+
                "VALUES(?, ?, ?)";
        sqLiteDatabase.execSQL(sql,new String[]{name,description, price});
        Toast.makeText(createProducts.this, "Product Details Added Successfully.", Toast.LENGTH_SHORT).show();

    }
    public void goBack(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}