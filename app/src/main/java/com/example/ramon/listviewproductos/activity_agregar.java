package com.example.ramon.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class activity_agregar extends AppCompatActivity {
    Spinner spinner;
    EditText txtNombre;
    Button btn;
    ArrayAdapter<String> adapter;
    String categorias[] = {"Electronica", "Hogar", "Video Juegos", "Informatica", "Electrodomesticos", "Juguetes"};
    int posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        spinner = (Spinner) findViewById(R.id.spnProducto);
        spinner.setAdapter(adapter);
        txtNombre = (EditText) findViewById(R.id.edTxtNombre);

        btn = (Button) findViewById(R.id.btnAgregar);
        getDataIntent();

    }

    //Metodo Agregar o Actualiza
    public void agregar(View v){
        if(btn.getText().equals("ACTUALIZAR")){
            Intent i = new Intent();
            i.putExtra("nombre",txtNombre.getText().toString());
            i.putExtra("categoria",spinner.getSelectedItem().toString());
            i.putExtra("aux","not");
            i.putExtra("pos",this.posicion);
            setResult(RESULT_OK,i);
            finish();
        }else{
            Intent i = new Intent();
            i.putExtra("nombre",txtNombre.getText().toString());
            i.putExtra("categoria",spinner.getSelectedItem().toString());
            i.putExtra("aux","ok");
            setResult(RESULT_OK,i);
            finish();
        }

    }

    //Metodo Obtener datos intent
    public void getDataIntent(){
        String producto = getIntent().getStringExtra("producto");
        String categoria = getIntent().getStringExtra("categoria");
        this.posicion = getIntent().getIntExtra("posicion", 0);
        if(producto != null){
            txtNombre.setText(producto);
            spinner.setSelection(adapter.getPosition(categoria));
            btn.setText("ACTUALIZAR");

        }
    }
}
