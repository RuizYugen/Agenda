package com.example.ruiz.agendav2.ObjetosYDaos;

public class Archivo {
    private int idArchivo;
    private String descripcion;
    private String tipo;
    private String ruta;
    private String titulo;

    public Archivo(int idArchivo, String descripcion, String tipo, String ruta, String titulo) {
        this.idArchivo = idArchivo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.ruta = ruta;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        tipo = tipo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        ruta = ruta;
    }
}
