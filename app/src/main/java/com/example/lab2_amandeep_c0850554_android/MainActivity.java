package com.example.lab2_amandeep_c0850554_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    SQLiteDatabase sqLiteDatabase;
    List<ProductDetails> productList;
    ListView list_view;
    TextView Product_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = findViewById(R.id.productList);
        Product_Search = findViewById(R.id.searchDet);
        productList = new ArrayList<>();
        sqLiteDatabase = openOrCreateDatabase("employee_db",MODE_PRIVATE,null);
        createTables();
        loadEmployees();
    }
    public void searchProducts(View view){
        Log.d("empty:","this is empty" + Product_Search.getText().toString());
        if(Product_Search.getText().toString().trim().isEmpty()){
            Log.d("empty:","this is empty");
            productList.clear();
            loadEmployees();
        }
        else {
            String sql = "SELECT * FROM products WHERE name = ?";
            Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{Product_Search.getText().toString().trim()});
            Log.d("cursor", "this is the: " + cursor.getCount());
            if (cursor == null || cursor.getCount() <= 0) {
                sql = "SELECT * FROM products WHERE description = ?";
                cursor = sqLiteDatabase.rawQuery(sql, new String[]{Product_Search.getText().toString().trim()});

            }
            if (cursor.moveToFirst()) {

                productList.clear();
                do {
                    // create an employee instance
                    Log.d("first name", "this is the " + cursor.getDouble(3));
                    productList.add(new ProductDetails(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getDouble(3)
                    ));
                    Log.d("Amandeep check", "this is" + productList.get(0).getPro_Name());
                } while (cursor.moveToNext());
                cursor.close();
            }
            productAdapter productAdapt = new productAdapter(this, R.layout.list_layout_products, productList, sqLiteDatabase);
            list_view.setAdapter(productAdapt);
        }
    }
    public void createTables(){
        String sql = "CREATE TABLE IF NOT EXISTS products(" +
                "id INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT,"+
                "name VARCHAR(20) NOT NULL,"+
                "description VARCHAR(150) NOT NULL,"+
                "price DOUBLE NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }
    public void addProducts(View view){
        startActivity(new Intent(MainActivity.this,createProducts.class));
    }
    private void addEmployee()
    {
        String name = "Watch";
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliqui";
        Double price = 159.99;
        String sql = "INSERT INTO products(name,description,price)"+
                "VALUES(?, ?, ?)";
        sqLiteDatabase.execSQL(sql,new String[]{name,description, String.valueOf(price)});

    }
    private void loadEmployees() {
        String sql = "SELECT * FROM products";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);



        if (cursor.moveToFirst()) {
            Log.d("here","we are here");
            do {
                // create an employee instance
                Log.d("first name","this is the "+cursor.getDouble(3));
                productList.add(new ProductDetails(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3)
                ));
                Log.d("Amandeep check","this is"+ productList.get(0).getPro_Name());
            } while (cursor.moveToNext());
            cursor.close();
        }

        productAdapter productAdapt = new productAdapter(this,R.layout.list_layout_products,productList,sqLiteDatabase);
        list_view.setAdapter(productAdapt);
    }
}