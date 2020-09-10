package com.example.produccionesnuberries;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class agregaRanchosActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap gMap;
    daoProductor dao;
    rancho r;
    Context context;
    int Productor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega_ranchos);
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if(extras!=null){
            Integer idProductor = extras.getInt("idProductor");
            Productor = idProductor;
        }


        context=getApplicationContext();
        dao = new daoProductor(context);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.Mapa);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        //Toast.makeText(agregaRanchosActivity.this,"Toque en el lugar donde quiere agregar el marcador.",Toast.LENGTH_LONG);
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(19.4145506,-101.9135773),8));
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Crear marcador
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude+" : "+latLng.longitude);//Se obtiene la latitud y la longitud
                //Limpiar la posición anterior
                gMap.clear();
                //Zoom al marcador
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                //Agregar el nuevo marcador
                gMap.addMarker(markerOptions);

                //Crear nuevo dialog con el formulario de agregar ranchos
                final Dialog dialog = new Dialog(agregaRanchosActivity.this);
                dialog.setTitle("Nuevo rancho");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.fragment_form_rancho);
                dialog.show();
                final TextView productor = (TextView)dialog.findViewById(R.id.txtidProductorRancho);
                final EditText nombreRancho = (EditText)dialog.findViewById(R.id.txtNombreRancho);
                final EditText municipioRancho =(EditText)dialog.findViewById(R.id.txtMunicipioRancho);
                final EditText estadoRancho =(EditText)dialog.findViewById(R.id.txtEstadoRancho);

                TextView lat = (TextView)dialog.findViewById(R.id.txtLat);
                TextView lng = (TextView)dialog.findViewById(R.id.txtLng);

                //Definimos latitud y longitud
                final String lt = new Double(latLng.latitude).toString();
                final String ln = new Double(latLng.longitude).toString();

                Button confirmar = (Button) dialog.findViewById(R.id.btnConfirmarRancho);
                Button cancelar = (Button)dialog.findViewById(R.id.btnCancelarRancho);
                lat.setText(lt);
                lng.setText(ln);
                productor.setText("Productor: "+Productor);//TODO: Siempre marca que es el productor 0

                /*
                TODO: Los ranchos no se guardan.
                */
                confirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if(null == dao){
                                dao = new daoProductor(context);
                            }
                            r=new rancho(Productor,
                                    nombreRancho.getText().toString(),
                                    municipioRancho.getText().toString(),
                                    estadoRancho.getText().toString(),
                                    lt,
                                    ln);
                            dao.insertarRancho(r);
                            Toast.makeText(agregaRanchosActivity.this,"Agregado",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(agregaRanchosActivity.this,MainActivity.class);
                            startActivity(intent);
                            /*
                            * W/System.err: java.lang.NullPointerException: Attempt to invoke virtual method 'boolean com.example.produccionesnuberries.daoProductor.insertarRancho(com.example.produccionesnuberries.rancho)' on a null object reference
                            * W/System.err:     at com.example.produccionesnuberries.agregaRanchosActivity$1$1.onClick(agregaRanchosActivity.java:98)
                             * */
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }

}
