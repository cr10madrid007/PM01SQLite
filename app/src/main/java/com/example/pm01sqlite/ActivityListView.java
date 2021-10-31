package com.example.pm01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pm01sqlite.configuracion.SQLiteConexion;
import com.example.pm01sqlite.configuracion.Transacciones;
import com.example.pm01sqlite.tablas.Personas;

import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    // variables globales
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion( this, Transacciones.NameDatabase, null, 1);
        listapersonas=(ListView) findViewById(R.id.listapersonas);

        obtenerListaPersonas();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);

    }


    private void obtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_personas = null;
        lista= new ArrayList<Personas>();

        // cursor de bd, nos apoya a recorrer la informacion

        Cursor cursor= db.rawQuery("SELECT * FROM "+Transacciones.tablaPersonas, null);

        // recorremos la informacion del cursor

        while (cursor.moveToNext()){
            list_personas= new Personas();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getInt(3));
            list_personas.setCorreo(cursor.getString(4));

            lista.add(list_personas);
        }
        cursor.close();

        fillList();

    }

    private void fillList(){
        ArregloPersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++){
            ArregloPersonas.add(lista.get(i).getId() + " | "
                               +lista.get(i).getNombres()+ " | "
                               +lista.get(i).getApellidos());
        }
    }


}