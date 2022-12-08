package com.example.descarga;

public class Descarga {

    //Atributos
    private final int imagen;

    private final String url;



    //Constructor
    public Descarga(int imagen, String url) {
        this.imagen = imagen;
        this.url = url;

    }

    //getters


    public int getImagen() {
        return imagen;
    }

    public String getTexto() {
        return url;
    }

}
