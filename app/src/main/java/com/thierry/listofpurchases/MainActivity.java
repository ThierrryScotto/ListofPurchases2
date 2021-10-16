package com.thierry.listofpurchases;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProducts;
    private ArrayAdapter adapter;
    private List<Product> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProducts = findViewById(R.id.lvProducts);
        loadProducts();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("action", "add");
                startActivity(intent);
            }
        });

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posistion, long l) {
                int idProduct = productsList.get(posistion).getId();

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("action", "edit");
                intent.putExtra("idProduct", idProduct);
                startActivity(intent);
            }
        });

        lvProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delete(position);
                return true;
            }
        });
    }

    private void delete(int position){
        final Product prod = productsList.get( position );
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete...");
        alert.setIcon(android.R.drawable.ic_delete);
        alert.setMessage("Allow the delete " + prod.getName() +"?");
        alert.setNeutralButton("Cancel", null);

        alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductDAO.delete(MainActivity.this, prod.getId());
                loadProducts();
            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Function that is part of the application lifecycle
    @Override
    protected void onRestart() {
        super.onRestart();
        loadProducts();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadProducts(){
        productsList = ProductDAO.getProducts(this);

        if (productsList.size() == 0) {
            Product fake = new Product("Empty List",  "", "0");
            productsList.add(fake);
            lvProducts.setEnabled(false);
        } else {
            lvProducts.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productsList);
        lvProducts.setAdapter(adapter);
    }
}