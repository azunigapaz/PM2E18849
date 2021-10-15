package com.grupoadec.pm2e18849.Tablas;

import android.content.Intent;

public class Paises {
    private Integer idpais;
    private String nombrepais;
    private String codigopais;

    // primer contructor de la clase
    public Paises(Integer id, String nombrepais, String codigopais) {
        this.idpais = id;
        this.nombrepais = nombrepais;
        this.codigopais = codigopais;
    }

    // segundo constructor de la clase
    public Paises(){};

    public Integer getId() { return idpais; }

    public void setId(Integer id) { this.idpais = id; }

    public String getNombrepais() { return nombrepais; }

    public void setNombrepais(String nombrepais) { this.nombrepais = nombrepais; }

    public String getCodigopais() { return codigopais; }

    public void setCodigopais(String codigopais) { this.codigopais = codigopais; }
}
