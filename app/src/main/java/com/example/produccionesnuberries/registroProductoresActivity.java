package com.example.produccionesnuberries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class registroProductoresActivity extends AppCompatActivity implements View.OnClickListener {
    daoProductor dao;
    Productor p;

    EditText nombreP, apellidopP, apellidomP, direccionP, telP, emailP, banco, plaza, numC;

    Button registrar, restablecer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_productores);
        dao= new daoProductor(this);
        //Datos productor
        nombreP=(EditText)findViewById(R.id.txtNombreP);
        apellidopP=(EditText)findViewById(R.id.txtapellidpP);
        apellidomP=(EditText)findViewById(R.id.txtapellidomP);
        direccionP=(EditText)findViewById(R.id.txtdireccionP);
        telP=(EditText)findViewById(R.id.txtTelP);
        emailP=(EditText)findViewById(R.id.txtEmailP);
        banco=(EditText)findViewById(R.id.txtBanco);
        plaza=(EditText)findViewById(R.id.txtPlaza);
        numC=(EditText)findViewById(R.id.txtBancoCuenta);


        registrar=(Button)findViewById(R.id.btnRegistrarPro);
        restablecer=(Button)findViewById(R.id.btnRestablecerFP);

        registrar.setOnClickListener(this);
        restablecer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrarPro:
                try{
                    p=new Productor(nombreP.getText().toString(),
                            apellidopP.getText().toString(),
                            apellidomP.getText().toString(),
                            direccionP.getText().toString(),
                            telP.getText().toString(),
                            emailP.getText().toString(),
                            (banco.getText().toString()+plaza.getText().toString()+numC.getText().toString()));
                    Bundle id = new Bundle();
                    id.putInt("idProductor",p.getId_productor());
                    dao.insertarProductor(p);
                    int idp=dao.getIdProductor(p);
                    Intent intent = new Intent(this,agregaRanchosActivity.class);
                    intent.putExtra("idProductor",idp);
                   // intent.putExtras(id);
                    Toast.makeText(registroProductoresActivity.this,"Productor "+ p.getId_productor()+ " agregado",Toast.LENGTH_LONG);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(registroProductoresActivity.this,"ERROR",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnRestablecerFP:

                break;
        }
    }

}
