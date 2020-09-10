package com.example.produccionesnuberries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoProductor {
    SQLiteDatabase cx;
    ArrayList<Productor> lista = new ArrayList<Productor>();
    ArrayList<rancho> listaRancho = new ArrayList<rancho>();
    Productor p;
    rancho r;
    Context ct;
    String nombreBD = "BDNUBerries";


    String tablaProductor = "CREATE TABLE IF NOT EXISTS productor(id_productor INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreProductor TEXT," +
            "apellidopProductor TEXT," +
            "apellidomProductor TEXT," +
            "direccionProductor TEXT," +
            "telefonoProductor TEXT," +
            "emailProductor TEXT," +
            "cIB TEXT)";

    String tablaRancho = "CREATE TABLE IF NOT EXISTS rancho (id_rancho INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreRancho TEXT," +
            "municipioRancho TEXT," +
            "estadoRancho TEXT," +
            "latitud TEXT," +
            "longitud TEXT," +
            "id_productor INTEGER," +
            "FOREIGN KEY(id_productor) REFERENCES productor(id_productor))";

    public daoProductor(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tablaProductor);
        cx.execSQL(tablaRancho);
    }


    public boolean insertarProductor(Productor p) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreProductor", p.getNombreProductor());
        contenedor.put("apellidopProductor", p.getApellidopProductor());
        contenedor.put("apellidomProductor", p.getApellidomProductor());
        contenedor.put("direccionProductor", p.getDireccionP());
        contenedor.put("telefonoProductor", p.getTelP());
        contenedor.put("emailProductor", p.getEmailP());
        contenedor.put("cIB", p.getcIB());

        return (cx.insert("productor", null, contenedor)) > 0;
    }

    public int getIdProductor(Productor p){

        Cursor cursor = cx.rawQuery("SELECT id_productor FROM productor WHERE nombreProductor='"+p.getNombreProductor()+"'",null);
        if(cursor!=null && cursor.getCount()>0) {
            cursor.moveToFirst();
        }
        return  cursor.getInt(cursor.getColumnIndex("id_productor"));
    }

    public boolean insertarRancho(rancho r) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreRancho", r.getNombreRancho());
        contenedor.put("municipioRancho", r.getMunicipioRancho());
        contenedor.put("estadoRancho", r.getEstadoRancho());
        contenedor.put("latitud", r.getLatitud());
        contenedor.put("longitud", r.getLongitud());
        contenedor.put("id_productor", r.getId_productor());
        return (cx.insert("rancho", null, contenedor)) > 0;
    }

    public boolean eliminarProductor(int id) {
        return (cx.delete("productor","id_productor="+id,null))>0;
    }

    public boolean editarProductor(Productor p) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreProductor", p.getNombreProductor());
        contenedor.put("apellidopProductor", p.getApellidopProductor());
        contenedor.put("apellidomProductor", p.getApellidomProductor());
        contenedor.put("direccionProductor", p.getDireccionP());
        contenedor.put("telefonoProductor", p.getTelP());
        contenedor.put("emailProductor", p.getEmailP());
        contenedor.put("cIB", p.getcIB());
        contenedor.put("id_contador", p.getId_contador());
        return (cx.update("productor", contenedor,"id_productor="+p.getId_productor(),null)) > 0;
    }

    public ArrayList<Productor> getListaProductor() {
        lista.clear();
        Cursor cursor = cx.rawQuery("SELECT * FROM productor",null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Productor(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)));
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<rancho> getListaRancho() {
        listaRancho.clear();
        Cursor cursor = cx.rawQuery("SELECT * FROM rancho",null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                listaRancho.add(new rancho(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return listaRancho;
    }

    public Productor verProductor(int posicion) {
        Cursor cursor = cx.rawQuery("SELECT * FROM productor",null);
        cursor.moveToPosition(posicion);
        p=new Productor(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7));
        return p;
    }
}
