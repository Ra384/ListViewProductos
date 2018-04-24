package com.example.ramon.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private ListView lista;
    private List<String> lProductos = new ArrayList<>();
    private List<String> lcategoria = new ArrayList<>();
    String textoProducto = "";
    String categoria ="";
    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.textView);
        lista = (ListView) findViewById(R.id.lista);

        actualizarTabla();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mSalir:
                System.exit(0);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String product = data.getStringExtra("nombre");
        if(data.getStringExtra("aux").equals("ok")){
            lProductos.add(product);
            lcategoria.add(data.getStringExtra("categoria"));
            actualizarTabla();
            Toast.makeText(this, "El producto "+product+" se agrego correctamente!", Toast.LENGTH_SHORT).show();
        }else{
            this.posicion = data.getIntExtra("pos",0);
            lProductos.set(posicion,product);
            lcategoria.set(posicion,data.getStringExtra("categoria"));
            Toast.makeText(this, "El producto "+product+" se modifico correctamente", Toast.LENGTH_SHORT).show();
            actualizarTabla();
        }

    }

    //Indicar que menu de contexto para visualizara al dejar presionado
    // los elementos asociados al context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //Obtengo elementos del listView
        this.textoProducto = ((TextView) info.targetView).getText().toString();
        this.posicion = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
        this.categoria = lcategoria.get(posicion);

        MenuInflater inflater = new MenuInflater(this);
        getMenuInflater().inflate(R.menu.menu_contexto,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mEditar:
                //Intent
                Intent i = new Intent(this,activity_agregar.class);
                i.putExtra("producto",this.textoProducto);
                i.putExtra("categoria",this.categoria);
                i.putExtra("posicion",this.posicion);
                startActivityForResult(i,111);
                break;
            case R.id.mEliminar:
                //Intent
                lProductos.remove(this.posicion);
                lcategoria.remove(this.posicion);
                actualizarTabla();
                Toast.makeText(this, "Eliminado Correctamente!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //Llama a la actividad para agregar un nuevo producto
    public void llamaActividad(View x){
        Intent i = new Intent(this,activity_agregar.class);
        startActivityForResult(i,123);
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

        //Asociamos al menu de contexto los elementos de la lista
        registerForContextMenu(lista);
    }


}
