package com.example.consurerestapi.entidades;

public class Pelicula {
    private int id;
    private String titulo;
    private int duracion;
    private String clasificacion;

    private String alanzamiento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getAlanzamiento() {
        return alanzamiento;
    }

    public void setAlanzamiento(String alanzamiento) {
        this.alanzamiento = alanzamiento;
    }
}
