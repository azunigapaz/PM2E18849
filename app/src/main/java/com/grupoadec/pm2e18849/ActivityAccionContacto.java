package com.grupoadec.pm2e18849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.RestrictionEntry;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.grupoadec.pm2e18849.Configuracion.SQLiteConexion;
import com.grupoadec.pm2e18849.Configuracion.Transacciones;

public class ActivityAccionContacto extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText txtconsultaidcontacto, txtconsultanombrecontacto, txtconsultacelcontacto, txtconsultanotacontacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accion_contacto);


        conexion = new SQLiteConexion(this,Transacciones.NameDatabase,null,1);

        Button btnllamarcontacto = (Button) findViewById(R.id.btnllamarcontacto);
        Button btnactualizarconsultacontacto = (Button) findViewById(R.id.btnactualizarconsultacontacto);
        Button btneliminarconsultacontacto = (Button) findViewById(R.id.btneliminarconsultacontacto);

        txtconsultaidcontacto = (EditText) findViewById(R.id.txtconsultaidcontacto);
        txtconsultanombrecontacto = (EditText) findViewById(R.id.txtconsultanombrecontacto);
        txtconsultacelcontacto = (EditText) findViewById(R.id.txtconsultacelcontacto);
        txtconsultanotacontacto = (EditText) findViewById(R.id.txtconsultanotacontacto);

        // extramemos los put que vienen del activity anterior
        String pxIdcontacto = getIntent().getStringExtra("pxIdcontacto");
        String pxNombre = getIntent().getStringExtra("pxNombre");
        String pxNumero = getIntent().getStringExtra("pxNumero");
        String pxNota = getIntent().getStringExtra("pxNota");

        // llenamos los EditText
        txtconsultaidcontacto.setText(pxIdcontacto);
        txtconsultanombrecontacto.setText(pxNombre);
        txtconsultacelcontacto.setText(pxNumero);
        txtconsultanotacontacto.setText(pxNota);


        btnllamarcontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtconsultaidcontacto.length() > 0 && txtconsultanombrecontacto.length() > 0 && txtconsultacelcontacto.length() > 0 && txtconsultanotacontacto.length() > 0) {
                    LlamarContacto();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe de llenar todos los campos para realizar la llamada",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnactualizarconsultacontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtconsultaidcontacto.length() > 0 && txtconsultanombrecontacto.length() > 0 && txtconsultacelcontacto.length() > 0 && txtconsultanotacontacto.length() > 0) {

                    ActualizarConsultaContacto();

                } else {
                    Toast.makeText(getApplicationContext(), "Debe de llenar todos los campos para actualizar el contacto",Toast.LENGTH_LONG).show();
                }
            }
        });

        btneliminarconsultacontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtconsultaidcontacto.length() > 0 && txtconsultanombrecontacto.length() > 0 && txtconsultacelcontacto.length() > 0 && txtconsultanotacontacto.length() > 0) {
                    EliminarConsultaContacto();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe de llenar todos los campos para eliminar el registro completo",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void LlamarContacto() {

    }

    private void ActualizarConsultaContacto() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = { txtconsultaidcontacto.getText().toString() };

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombrecontacto, txtconsultanombrecontacto.getText().toString());
        valores.put(Transacciones.numerocontacto, txtconsultacelcontacto.getText().toString());
        valores.put(Transacciones.notacontacto, txtconsultanotacontacto.getText().toString());

        db.update(Transacciones.tablacontactos, valores, Transacciones.idcontacto + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_LONG).show();

        ClearScreen();

        db.close();
    }

    private void EliminarConsultaContacto() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = { txtconsultaidcontacto.getText().toString() };

        String wherecond = Transacciones.idcontacto + "=?";

        db.delete(Transacciones.tablacontactos, wherecond, params);

        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        ClearScreen();

    }

    private void ClearScreen() {

        txtconsultaidcontacto.setText("");
        txtconsultanombrecontacto.setText("");
        txtconsultacelcontacto.setText("");
        txtconsultanotacontacto.setText("");

    }

}