package com.example.produccionesnuberries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap gMap;
    daoProductor daoProductor;
    private Marker marker,miMarker;
    double lat = 0.0;
    double lng = 0.0;
    TextView coord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapRoutes);
        supportMapFragment.getMapAsync(this);
        coord = (TextView) findViewById(R.id.txtLatLngGPS);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        miUbicación();
        colocarMarcadores(gMap);
    }

    private void colocarMarcadores(GoogleMap googleMap) {
        ArrayList<rancho> ranchos = new ArrayList<>();
        daoProductor = new daoProductor(MapsActivity.this);
        ranchos = daoProductor.getListaRancho();
        for (int i = 0; i < ranchos.size(); i++) {
            double latitud = Double.parseDouble(ranchos.get(i).getLatitud());
            double longitud = Double.parseDouble(ranchos.get(i).getLongitud());
            String nombre = ranchos.get(i).nombreRancho;
            LatLng coordenadas = new LatLng(latitud, longitud);
            //if (marker != null) marker.remove();
            marker = googleMap.addMarker(new MarkerOptions()
                    .position(coordenadas)
                    .title("Rancho " + nombre)
                    .snippet("Municipio de " + ranchos.get(i).getMunicipioRancho()));
        }
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (miMarker != null) miMarker.remove();
        miMarker = gMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Posición actual").snippet("Latitud: " + lat + ", Longitud: " + lng));
        gMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicación(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
            coord.setText(lat + ", " + lng);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicación(location);
            if (gMap != null) {
                colocarMarcadores(gMap);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicación() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicación(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locationListener);
    }
}
