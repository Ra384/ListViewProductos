package com.example.ramon.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private ListView lista;
    
    //Datos
    /*private String productos[] = {
            "Computadora", "Mouse", "Dulces", "Hojas",
            "Lapices", "Lentes","Reloj", "Cuchara", "Celular", "Mesa", "Refrigerador",
            "Horno", "Audifonos"
    };
    private String categoria[] = {
            "Electronica","Electronica","Dulceria","Papeleria","Papeleria","Moda","Perfumeria ","Hogar",
            "Electronicos", "Hogar", "Electrodomesticos", "Electrodomesticos","Electronica"
    };*/

    private List<String> lProductos = new ArrayList<>();
    private List<String> lcategoria = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.textView);
        lista = (ListView) findViewById(R.id.lista);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,productos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {
                txt.setText("Categoria elegida -" + categoria[position]);
            }
        });*/

        actualizarTabla();
    }

    public void llamaActividad(View x){
        Intent i = new Intent(this,activity_agregar.class);
        startActivityForResult(i,123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lProductos.add(data.getStringExtra("nombre"));
        lcategoria.add(data.getStringExtra("categoria"));
        actualizarTabla();
    }

    //Actualiza datos cada vez que se agregue los datos desde la otra actividad.
    public void actualizarTabla(){
        String productos[] = new String[lProductos.size()];
        lProductos.toArray(productos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,productos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                txt.setText("Categoria Elegida : "+lcategoria.get(position));
            }
        });
    }


}
