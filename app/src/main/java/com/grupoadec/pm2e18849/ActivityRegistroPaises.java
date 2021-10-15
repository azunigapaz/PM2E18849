package com.grupoadec.pm2e18849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grupoadec.pm2e18849.Configuracion.SQLiteConexion;
import com.grupoadec.pm2e18849.Configuracion.Transacciones;

public class ActivityRegistroPaises extends AppCompatActivity {

    EditText txtnombrepais;
    EditText txtcodigopais;
    Button btnagregarpais;
    Button btnconsultapaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paises);

        txtnombrepais = (EditText) findViewById(R.id.txtnombrepais);
        txtcodigopais = (EditText) findViewById(R.id.txtcodigopais);
        btnagregarpais = (Button) findViewById(R.id.btnagregarpais);
        btnconsultapaises = (Button) findViewById(R.id.btnconsultapaises);

        btnagregarpais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPais();
            }
        });

        btnconsultapaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityConsultaPaises.class);
                startActivity(intent);
            }
        });


    }

    private void AgregarPais() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombrepais, txtnombrepais.getText().toString());
        valores.put(Transacciones.codigopais, txtcodigopais.getText().toString());

        Long resultado = db.insert(Transacciones.tablapaises, Transacciones.idpais,valores);

        Toast.makeText(getApplicationContext(),"Registro ingresado: id: " + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();

    }

    private void LimpiarPantalla() {
        txtnombrepais.setText("");
        txtcodigopais.setText("");
    }
}