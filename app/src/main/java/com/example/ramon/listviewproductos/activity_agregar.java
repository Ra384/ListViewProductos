package com.example.ramon.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class activity_agregar extends AppCompatActivity {
    Spinner spinner;
    EditText txtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        String categorias[] = {"Electronica","Hogar","Video Juegos","Informatica","Electrodomesticos","Juguetes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categorias);
        spinner = (Spinner) findViewById(R.id.spnProducto);
        spinner.setAdapter(adapter);

        txtNombre = (EditText) findViewById(R.id.edTxtNombre);
    }

    //Metodo Agregar
    public void agregar(View v){
        Intent i = new Intent();
        i.putExtra("nombre",txtNombre.getText().toString());
        i.putExtra("categoria",spinner.getSelectedItem().toString());
        setResult(RESULT_OK,i);
        finish();
    }
}
