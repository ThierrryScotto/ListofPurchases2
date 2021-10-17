package com.thierry.listofpurchases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private EditText etProductName;
    private EditText etQuantity;
    private Spinner spSupermarket;
    private Button btSave;
    private Button btCancel;
    private String action;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etProductName = findViewById(R.id.etProductName);
        etQuantity = findViewById(R.id.etQuantity);
        spSupermarket = findViewById(R.id.spSupermarket);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);

        action = getIntent().getStringExtra("action");
        if (action.equals("edit")) {
            loadForm();
        }

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanForm();
            }
        });
    }

    private void loadForm() {
        int id = getIntent().getIntExtra("idProduct", 0);
        product = ProductDAO.getProductById(this, id);
        etProductName.setText( product.getName() );
        etQuantity.setText( product.getQuantity() );

        String[] supermarkets = getResources().getStringArray(R.array.listOfSupermarkets);
        for (int i = 1; i < supermarkets.length ;i++){
            if( product.getSupermarket().equals( supermarkets[i] ) ){
                spSupermarket.setSelection(i);
                break;
            }
        }
    }

    private void save(){
        String name = etProductName.getText().toString();
        String quantity = etQuantity.getText().toString();

        if (name.isEmpty() || spSupermarket.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG);
        } else {
            if( action.equals("add")) {
                product = new Product();
            }
            product.setName( name );
            product.setQuantity( quantity );
            product.setSupermarket( spSupermarket.getSelectedItem().toString() );
            if( action.equals("add")) {
                ProductDAO.add(this, product);
                etProductName.setText("");
                etQuantity.setText("");
                spSupermarket.setSelection(0, true);
            }else{
                ProductDAO.edit(this, product);
                finish();
            }
        }
    }

    private void cleanForm(){
        etProductName.setText( "" );
        etQuantity.setText( "0" );
        spSupermarket.setSelection(0, true);
    }
}