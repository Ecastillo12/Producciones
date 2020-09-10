package com.example.produccionesnuberries;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter implements View.OnClickListener {
    ArrayList<Productor> listaProductor = new ArrayList<Productor>();
    daoProductor dao;
    Productor p;
    Activity a;
    int id_productor=0;

    public Adaptador(ArrayList<Productor> listaProductor, Activity a, daoProductor dao){
        this.listaProductor=listaProductor;
        this.a=a;
        this.dao=dao;
    }

    public int getId_productor() {
        return id_productor;
    }

    public void setId_productor(int id_productor) {
        this.id_productor = id_productor;
    }


    @Override
    public int getCount() {
        return listaProductor.size();
    }

    @Override
    public Productor getItem(int position) {
        p=listaProductor.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        p=listaProductor.get(position);
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v==null){
            LayoutInflater layoutInflater=(LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=layoutInflater.inflate(R.layout.item_productor,null);
        }
        p=listaProductor.get(i);

        setId_productor(p.getId_productor());
        TextView nombre =(TextView)v.findViewById(R.id.t_nombreItemProductor);
        TextView cIB =(TextView)v.findViewById(R.id.t_claveItemProductor);
        TextView apellidos =(TextView)v.findViewById(R.id.t_ApellidosItemProductor);
        TextView direccion =(TextView)v.findViewById(R.id.t_direccionPItemProductor);
        TextView tel =(TextView)v.findViewById(R.id.t_TelItemProductor);
        TextView email =(TextView)v.findViewById(R.id.t_EmailPItemProductor);

        ImageButton editar =(ImageButton)v.findViewById(R.id.btnEditItem);
        ImageButton delete =(ImageButton)v.findViewById(R.id.btnDeleteItem);

        nombre.setText(p.getNombreProductor());
        cIB.setText(p.getcIB());
        apellidos.setText(p.getApellidopProductor()+" "+p.getApellidomProductor());
        direccion.setText(p.getDireccionP());
        tel.setText(p.getTelP());
        email.setText(p.getEmailP());

        editar.setTag(i);
        delete.setTag(i);
        editar.setOnClickListener(this);
        delete.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditItem:
                //Dialogo de editar
                int pos=Integer.parseInt(v.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar productor");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_registro_productores);
                dialog.show();
                final EditText nombreP=(EditText)dialog.findViewById(R.id.txtNombreP);
                final EditText apellidopP=(EditText)dialog.findViewById(R.id.txtapellidpP);
                final EditText apellidomP=(EditText)dialog.findViewById(R.id.txtapellidomP);
                final EditText direccionP=(EditText)dialog.findViewById(R.id.txtdireccionP);
                final EditText telP=(EditText)dialog.findViewById(R.id.txtTelP);
                final EditText emailP=(EditText)dialog.findViewById(R.id.txtEmailP);
                final EditText banco=(EditText)dialog.findViewById(R.id.txtBanco);
                final EditText plaza=(EditText)dialog.findViewById(R.id.txtPlaza);
                final EditText numC=(EditText)dialog.findViewById(R.id.txtBancoCuenta);



                Button registrar=(Button)dialog.findViewById(R.id.btnRegistrarPro);
                Button restablecer=(Button)dialog.findViewById(R.id.btnRestablecerFP);

                p=listaProductor.get(pos);

                nombreP.setText(p.getNombreProductor());
                apellidopP.setText(p.getApellidopProductor());
                apellidomP.setText(p.getApellidomProductor());
                direccionP.setText(p.getDireccionP());
                telP.setText(p.getTelP());
                emailP.setText(p.getEmailP());

                registrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            p=new Productor(getId_productor(),nombreP.getText().toString(),
                                    apellidopP.getText().toString(),
                                    apellidomP.getText().toString(),
                                    direccionP.getText().toString(),
                                    telP.getText().toString(),
                                    emailP.getText().toString(),
                                    (banco.getText().toString()+plaza.getText().toString()+numC.getText().toString()));
                            dao.editarProductor(p);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){

                        }
                    }
                });

                restablecer.setOnClickListener(this);

                break;
            case R.id.btnDeleteItem:
                int posi=Integer.parseInt(v.getTag().toString());
                p=listaProductor.get(posi);
                setId_productor(p.getId_productor());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estás seguro de eliminar productor?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.eliminarProductor(getId_productor());
                        listaProductor=dao.getListaProductor();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                del.show();
                break;
        }
    }
}
