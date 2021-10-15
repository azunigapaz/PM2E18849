package com.grupoadec.pm2e18849.Tablas;

public class Contactos {
    private Integer idcontacto;
    private Integer idpais;
    private String nombrecontacto;
    private String numerocontacto;
    private String notacontacto;

    // primer constructor de la clase
    public Contactos(Integer idcontacto, Integer idpais, String nombrecontacto, String numerocontacto, String notacontacto) {
        this.idcontacto = idcontacto;
        this.idpais = idpais;
        this.nombrecontacto = nombrecontacto;
        this.numerocontacto = numerocontacto;
        this.notacontacto = notacontacto;
    }

    // segundo constructor de la clase
    public Contactos(){};

    public Integer getIdcontacto() { return idcontacto; }

    public void setIdcontacto(Integer idcontacto) {  this.idcontacto = idcontacto; }

    public Integer getIdpais() { return idpais; }

    public void setIdpais(Integer idpais) { this.idpais = idpais; }

    public String getNombrecontacto() {  return nombrecontacto; }

    public void setNombrecontacto(String nombrecontacto) { this.nombrecontacto = nombrecontacto; }

    public String getNumerocontacto() { return numerocontacto; }

    public void setNumerocontacto(String numerocontacto) { this.numerocontacto = numerocontacto;  }

    public String getNotacontacto() { return notacontacto;  }

    public void setNotacontacto(String notacontacto) {  this.notacontacto = notacontacto; }
}
