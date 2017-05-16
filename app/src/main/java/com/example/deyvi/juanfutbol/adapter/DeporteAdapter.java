package com.example.deyvi.juanfutbol.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deyvi.juanfutbol.DetalleEvento;
import com.example.deyvi.juanfutbol.R;
import com.example.deyvi.juanfutbol.pojo.Evento;

import java.util.ArrayList;

/**
 * Created by deyvi on 26/04/2017.
 */


public class DeporteAdapter extends RecyclerView.Adapter<DeporteAdapter.DeporteViewHolder> {

    Activity activity;

    ArrayList<Evento> eventos;

    public DeporteAdapter( Activity activity, ArrayList<Evento> eventos) {

        this.activity = activity;
        this.eventos = eventos;
    }

    @Override
    public DeporteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carview_depote,parent, false);
        return  new DeporteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DeporteViewHolder holder, int position) {
        final Evento evento= eventos.get(position);
        holder.name.setText(evento.getName());
        //hay que pasarle un context pero le paso la actividad para que el constructor no lleve muchas coasas
        Glide.with(activity).load(evento.getImage()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,DetalleEvento.class);
                intent.putExtra("evento",evento);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }


    public static class DeporteViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;

        public DeporteViewHolder(View itemView) {
            super(itemView);

        name      = (TextView)itemView.findViewById(R.id.tvName);
        image     = (ImageView) itemView.findViewById(R.id.imgDeporte);

        }
    }

}
