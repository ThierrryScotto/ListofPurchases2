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
    private Spinner spCategory;
    private Button btSave;
    private Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etProductName = findViewById(R.id.etProductName);
        spCategory = findViewById(R.id.spCategory);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);

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

    private void save(){
        String name = etProductName.getText().toString();

        if (name.isEmpty() || spCategory.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG);
        } else {
            Product product = new Product();
            product.setName( name );
            product.setCategory( spCategory.getSelectedItem().toString() );

            ProductDAO.add(this, product);
            cleanForm();
        }
    }

    private void cleanForm(){
        etProductName.setText("");
        spCategory.setSelection(0, true);
    }
}