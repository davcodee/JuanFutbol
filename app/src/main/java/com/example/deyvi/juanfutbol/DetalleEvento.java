package com.example.deyvi.juanfutbol;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.deyvi.juanfutbol.pojo.Evento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DetalleEvento extends AppCompatActivity  {
    private TextView  tvTitulo;
    private ImageView imgEvento;
    private TextView  tvDescription;
    private TextView  tvCategoria;
    private TextView  tvType;
    private TextView  tvLugar;
    private TextView  tvDate;
    private ImageView imgYotube;
    private ImageView imgMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

        tvTitulo      =(TextView) findViewById(R.id.tvTitulo);
        imgEvento     =(ImageView)findViewById(R.id.imgEvento);
        tvDescription =(TextView) findViewById(R.id.tvDescription);
        tvCategoria   =(TextView) findViewById(R.id.tvCategoria);
        tvType        =(TextView) findViewById(R.id.tvType);
        tvLugar       =(TextView) findViewById(R.id.tvLugar);
        tvDate        =(TextView)findViewById(R.id.tvDate);
        imgYotube     = (ImageView) findViewById(R.id.imgYoutube);
        imgMaps       =(ImageView) findViewById(R.id.imgMaps);


        Bundle bundle = getIntent().getExtras();

        final Evento evento = (Evento) bundle.get("evento");

        tvTitulo.setText(evento.getName());
        Glide.with(this).load(evento.getImage()).into(imgEvento);
        tvDescription.setText(evento.getDescription());
        tvCategoria.setText(evento.getCategory());
        tvType.setText(evento.getType());
        tvLugar.setText(evento.getPlace());

        //para el  date unix de nuestra fecha hay que convertirlos
        Date date = new Date(evento.getDateUnix()*1000); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        tvDate.setText(formattedDate);

        //Para que habra nuestro video de youtube
        imgYotube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(evento.getVideo())));
            }
        });

        imgMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hayq que agregar lo de pedir permiso para el usuario
                // Tambien hay que agregar lo de la actividad de mapas par a  marcar ubuicacion de nuestro user
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });
    }


}
