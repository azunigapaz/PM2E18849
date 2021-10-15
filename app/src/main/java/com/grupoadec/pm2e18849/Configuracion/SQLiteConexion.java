package com.grupoadec.pm2e18849.Configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConexion extends SQLiteOpenHelper {


    public SQLiteConexion(@Nullable Context context, @Nullable String dbname, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        // definimos el constructor de la clase
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos las tablas de la base de datos
        db.execSQL(Transacciones.CreateTablePaises);
        db.execSQL(Transacciones.CreateTableContactos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // eliminamos las tablas de la base de datos, funcion para limpiar la db
        db.execSQL(Transacciones.DropTableContactos);
        db.execSQL(Transacciones.DROPTablePaises);

        // Cramos nuevamente las tablas
        onCreate(db);
    }
}
