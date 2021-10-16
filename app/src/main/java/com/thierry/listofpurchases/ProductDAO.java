package com.thierry.listofpurchases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static void add(Context context, Product product){
        ContentValues values = new ContentValues();

        values.put("name", product.getName());
        values.put("category", product.getCategory());
        values.put("quantity", product.getQuantity());

        Database database = new Database(context);
        SQLiteDatabase db = database.getWritableDatabase();

        db.insert("Products", null, values);
    }

    public static void edit(Context context, Product product){
        ContentValues values = new ContentValues();

        values.put("name", product.getName());
        values.put("category", product.getCategory());
        values.put("quantity", product.getQuantity());

        Database database = new Database(context);
        SQLiteDatabase db = database.getWritableDatabase();

        db.update("Products", values, "id = "+product.getId(), null);
    }

    public static void delete(Context context, int idProduct){
        Database database = new Database(context);
        SQLiteDatabase db = database.getWritableDatabase();

        db.delete("Products","id = "+idProduct, null);
    }

    public static List<Product> getProducts(Context context){
        List<Product> list = new ArrayList<>();

        Database database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Products", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setCategory(cursor.getString(2));
                product.setQuantity(cursor.getString(3));
                list.add(product);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public static Product getProductById(Context context, int idProduct){
        Database database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Products WHERE id = "+idProduct, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setCategory(cursor.getString(2));
            product.setQuantity(cursor.getString(3));

            return product;
        }

        return null;
    }
}
