package com.example.produccionesnuberries;

public class Productor {
    int id_productor, id_contador;
    String nombreProductor, apellidopProductor, apellidomProductor, direccionP, telP, emailP;
    String cIB;

    public Productor() {
    }

    public Productor(String nombreProductor, String apellidopProductor, String apellidomProductor, String direccionP, String telP, String emailP, String cIB) {
        this.nombreProductor = nombreProductor;
        this.apellidopProductor = apellidopProductor;
        this.apellidomProductor = apellidomProductor;
        this.direccionP = direccionP;
        this.telP = telP;
        this.emailP = emailP;
        this.cIB = cIB;
    }

    public Productor(int id_contador, String nombreProductor, String apellidopProductor, String apellidomProductor, String direccionP, String telP, String emailP, String cIB) {
        this.id_contador = id_contador;
        this.nombreProductor = nombreProductor;
        this.apellidopProductor = apellidopProductor;
        this.apellidomProductor = apellidomProductor;
        this.direccionP = direccionP;
        this.telP = telP;
        this.emailP = emailP;
        this.cIB = cIB;
    }

    public Productor(int id_productor, int id_contador, String nombreProductor, String apellidopProductor, String apellidomProductor, String direccionP, String telP, String emailP, String cIB) {
        this.id_productor = id_productor;
        this.id_contador = id_contador;
        this.nombreProductor = nombreProductor;
        this.apellidopProductor = apellidopProductor;
        this.apellidomProductor = apellidomProductor;
        this.direccionP = direccionP;
        this.telP = telP;
        this.emailP = emailP;
        this.cIB = cIB;
    }

    public int getId_productor() {
        return id_productor;
    }

    public void setId_productor(int id_productor) {
        this.id_productor = id_productor;
    }

    public int getId_contador() {
        return id_contador;
    }

    public void setId_contador(int id_contador) {
        this.id_contador = id_contador;
    }

    public String getNombreProductor() {
        return nombreProductor;
    }

    public void setNombreProductor(String nombreProductor) {
        this.nombreProductor = nombreProductor;
    }

    public String getApellidopProductor() {
        return apellidopProductor;
    }

    public void setApellidopProductor(String apellidopProductor) {
        this.apellidopProductor = apellidopProductor;
    }

    public String getApellidomProductor() {
        return apellidomProductor;
    }

    public void setApellidomProductor(String apellidomProductor) {
        this.apellidomProductor = apellidomProductor;
    }

    public String getDireccionP() {
        return direccionP;
    }

    public void setDireccionP(String direccionP) {
        this.direccionP = direccionP;
    }

    public String getTelP() {
        return telP;
    }

    public void setTelP(String telP) {
        this.telP = telP;
    }

    public String getEmailP() {
        return emailP;
    }

    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }

    public String getcIB() {
        return cIB;
    }

    public void setcIB(String cIB) {
        this.cIB = cIB;
    }
}
