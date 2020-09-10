package com.example.produccionesnuberries;

public class rancho {
    int id_rancho, id_productor;
    String nombreRancho, municipioRancho, estadoRancho, latitud, longitud;

    public rancho() {
    }

    public rancho(int id_productor, String nombreRancho, String municipioRancho, String estadoRancho, String latitud, String longitud) {
        this.id_productor = id_productor;
        this.nombreRancho = nombreRancho;
        this.municipioRancho = municipioRancho;
        this.estadoRancho = estadoRancho;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public rancho(int id_rancho, int id_productor, String nombreRancho, String municipioRancho, String estadoRancho, String latitud, String longitud) {
        this.id_rancho = id_rancho;
        this.id_productor = id_productor;
        this.nombreRancho = nombreRancho;
        this.municipioRancho = municipioRancho;
        this.estadoRancho = estadoRancho;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId_rancho() {
        return id_rancho;
    }

    public void setId_rancho(int id_rancho) {
        this.id_rancho = id_rancho;
    }

    public int getId_productor() {
        return id_productor;
    }

    public void setId_productor(int id_productor) {
        this.id_productor = id_productor;
    }

    public String getNombreRancho() {
        return nombreRancho;
    }

    public void setNombreRancho(String nombreRancho) {
        this.nombreRancho = nombreRancho;
    }

    public String getMunicipioRancho() {
        return municipioRancho;
    }

    public void setMunicipioRancho(String municipioRancho) {
        this.municipioRancho = municipioRancho;
    }

    public String getEstadoRancho() {
        return estadoRancho;
    }

    public void setEstadoRancho(String estadoRancho) {
        this.estadoRancho = estadoRancho;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
