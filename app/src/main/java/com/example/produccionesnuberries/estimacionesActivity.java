package com.example.produccionesnuberries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class estimacionesActivity extends AppCompatActivity{
    Spinner productores, frutas;
    EditText diarioText, semanalText, mensualText;
    String fruta="",productor="";

    Button aceptar,cancelar;

    daoProductor daoProductor;
    ArrayList<String> listaNombres;
    ArrayList<Productor> listaProductores;
    //Productor p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimaciones);
        productores = (Spinner)findViewById(R.id.Spin);
        frutas = (Spinner)findViewById(R.id.spinFruta);

        diarioText=(EditText)findViewById(R.id.intDiario);
        semanalText=(EditText)findViewById(R.id.intSemanal);
        mensualText=(EditText)findViewById(R.id.intMensual);

        aceptar = (Button)findViewById(R.id.btnAceptarEst);
        cancelar=(Button)findViewById(R.id.btnCancelarEst);
        daoProductor=new daoProductor(getApplicationContext());
        obtenerLista(); //Marca error Unable to start activity ComponentInfo,
        //Caused by: java.lang.NullPointerException: Attempt to invoke virtual method
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaNombres);
        //String[] listaProductores = {"1 - Lalo Castillo", "2 - Enrique Velez", "3 - Alberto Lozano"};
        //productores.setAdapter(new ArrayAdapter<String>(estimacionesActivity.this,android.R.layout.simple_spinner_item,listaProductores));
        productores.setAdapter(adapter);
        productores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if(item!=null){
                    productor=item.toString();
                }
                Toast.makeText(estimacionesActivity.this,productor+" seleccionado",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] listaFrutas = {"Arándano", "Zarzamora", "Mora azul", "Fresa"};
        frutas.setAdapter(new ArrayAdapter<String>(estimacionesActivity.this,android.R.layout.simple_spinner_item,listaFrutas));
        frutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if(item!=null){
                    fruta=item.toString();
                }
                Toast.makeText(estimacionesActivity.this,fruta+" Seleccionado",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(estimacionesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (diarioText.getText().toString() != "") {
                        int diario = Integer.parseInt(diarioText.getText().toString());
                        int semanal = Integer.parseInt(semanalText.getText().toString());
                        int mensual = Integer.parseInt(mensualText.getText().toString());
                        int[] valores = new int[]{diario, semanal, mensual};

                        Intent intent = new Intent(estimacionesActivity.this, GraficosActivity.class);
                        intent.putExtra("prod", valores);
                        startActivity(intent);
                    } else {

                    }
                }catch (Exception e){
                    Toast.makeText(estimacionesActivity.this, "Es necesario llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void obtenerLista(){
        listaNombres=new ArrayList<String>();
        if(daoProductor.getListaProductor()==null){
            Toast.makeText(this,"A ocurrido un error", Toast.LENGTH_LONG);
        }else {
            listaProductores = daoProductor.getListaProductor();
            listaNombres.add("Seleccione");
            for (int i = 0; i < listaProductores.size(); i++) {
                listaNombres.add(daoProductor.getIdProductor(listaProductores.get(i)) + " - " + listaProductores.get(i).getNombreProductor());
            }
        }
    }

}
