package com.example.pm01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.autofill.CharSequenceTransformation;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pm01sqlite.configuracion.SQLiteConexion;
import com.example.pm01sqlite.configuracion.Transacciones;
import com.example.pm01sqlite.tablas.Personas;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    //variables Globales
    SQLiteConexion conexion;
    Spinner combopersonas;
    EditText txtnombres, txtapellidos, txtedad;

    ArrayList<String> listapersonas;
    ArrayList<Personas> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        combopersonas=(Spinner) findViewById(R.id.combopersonas);
        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtapellidos = (EditText) findViewById(R.id.txtapellido);
        txtedad = (EditText) findViewById(R.id.txtyears);

        obtenerListaPersonas();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listapersonas);
        combopersonas.setAdapter(adp);

       //Evento de seleccion del combo
        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtnombres.setText(lista.get(i).getNombres());
                txtapellidos.setText(lista.get(i).getApellidos());
                txtedad.setText(lista.get(i).getEdad().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

        fillCombo();

    }

    private void fillCombo() {
        listapersonas = new ArrayList<String>();
        for(int i=0; i< lista.size(); i++){
            listapersonas.add(lista.get(i).getNombres() + " | "
                            +lista.get(i).getApellidos() + " | "
                            +lista.get(i).getEdad());
        }
    }

}