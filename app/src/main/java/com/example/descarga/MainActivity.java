package com.example.descarga;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button botonDescargas;
    RecyclerView recyclerView;
    URL url;
    int permiso;
    List<Descarga> lista = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permiso != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 0);
        }
        editText = findViewById(R.id.edittext);
        botonDescargas = findViewById(R.id.botonDescargas);
        recyclerView = findViewById(R.id.rv);
        botonDescargas.setOnClickListener(this::descargar);

    }

    public void descargar(View v) {

        try {


            //Conseguimos la url que del EditText
            url = new URL(editText.getText().toString());
            //Creamos la solicitud
            DownloadManager.Request solicitud = new DownloadManager.Request(Uri.parse(String.valueOf(url)));
            solicitud.setTitle("Mi descarga");
            //Permitimos los tipos de red para descargar los archivos

            solicitud.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

            solicitud.allowScanningByMediaScanner();
            solicitud.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            //Obtengo el servicio de descarga y pongo en cola el archivo
            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(solicitud);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            lista.add(0, new Descarga(R.drawable.tick, String.valueOf(editText.getText())));
            recyclerView.setAdapter(new adapter(lista));

        } catch (MalformedURLException e) {

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            lista.add(0, new Descarga(R.drawable.interrogacion, String.valueOf(editText.getText())));
            recyclerView.setAdapter(new adapter(lista));
        }
    }
}