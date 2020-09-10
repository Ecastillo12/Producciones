package com.example.produccionesnuberries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton catFrutas, catTotal, Rutas;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catFrutas = (ImageButton)findViewById(R.id.imgBtnCatFrut);
        catTotal = (ImageButton)findViewById(R.id.imgBtnCatTot);
        Rutas = (ImageButton)findViewById(R.id.imgBtnRutas);
        imageView = (ImageView)findViewById(R.id.imgLogo);

        catFrutas.setOnClickListener(this);
        catTotal.setOnClickListener(this);
        Rutas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imgBtnCatFrut:
                Intent intent = new Intent(this,estimacionesActivity.class);
                startActivity(intent);
                break;
            case R.id.imgBtnCatTot:
                Intent intent2 = new Intent(this,productoresActivity.class);
                startActivity(intent2);
                break;
            case R.id.imgBtnRutas:
                Intent intent3 = new Intent(this,MapsActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
