package com.example.produccionesnuberries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class productoresActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton agregarP;
    daoProductor dao;
    Adaptador adaptador;
    ArrayList<Productor> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productores);
        dao = new daoProductor(this);
        lista=dao.getListaProductor();
        adaptador=new Adaptador(lista, this, dao);
        ListView listView =(ListView)findViewById(R.id.listaProductores);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        agregarP=(FloatingActionButton)findViewById(R.id.btnAgregarP);
        agregarP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregarP:
                Intent intent = new Intent(this,registroProductoresActivity.class);
                startActivity(intent);
                break;
        }
    }
}
