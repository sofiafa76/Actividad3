package com.example.descarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    List<Descarga> descarga;

    //Constructor
    public adapter(List<Descarga> modelList) {
        descarga=modelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(descarga.get(position));
    }

    @Override
    public int getItemCount() {
        return descarga.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imagen;
        private final TextView link;
        ProgressBar pb;
        ViewHolder(View v) {
            super(v);
            imagen=v.findViewById(R.id.imagen);
            link=v.findViewById(R.id.texto);

        }
        public void bind(Descarga descarga){
            imagen.setImageResource(descarga.getImagen());
            link.setText(descarga.getTexto());
        }

    }
}