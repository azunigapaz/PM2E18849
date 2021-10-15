package com.grupoadec.pm2e18849.Configuracion;

public class Transacciones {

    // database Name
    public static final String NameDatabase = "ContactosDB";

    /* db tables */
    // tabla paises
    public static final String tablapaises = "paises";

    // campos de la tabla paises
    public static final String idpais = "idpais";
    public static final String nombrepais = "nombrepais";
    public static final String codigopais = "codigopais";

    // tabla contactos
    public static final String tablacontactos = "contactos";

    // campos tabla contactos
    public static final String idcontacto = "idcontacto";
    public static final String fkidpais = "idpais";
    public static final String nombrecontacto = "nombrecontacto";
    public static final String numerocontacto = "numerocontacto";
    public static final String notacontacto = "notacontacto";

    /* Transacciones DDL(Data Definition Language) */
    // tabla paises
    public static final String CreateTablePaises = "CREATE TABLE paises (idpais INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombrepais TEXT, codigopais TEXT)";

    public static final String DROPTablePaises = "DROP TABLE IF EXISTS paises";

    // tabla contactos
    public static final String CreateTableContactos = "CREATE TABLE contactos (idcontacto INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idpais INTEGER NOT NULL, FOREIGN KEY (idpais) REFERENCES paises (idpais)," +
            "nombrecontacto TEXT, numerocontacto TEXT, notacontacto TEXT)";

    public static final String DropTableContactos = "DROP TABLE ID EXISTS contactos";

}
