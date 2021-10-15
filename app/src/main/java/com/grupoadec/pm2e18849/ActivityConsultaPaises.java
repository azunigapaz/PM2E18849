package com.grupoadec.pm2e18849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.grupoadec.pm2e18849.Configuracion.SQLiteConexion;
import com.grupoadec.pm2e18849.Configuracion.Transacciones;

public class ActivityConsultaPaises extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id, nombrepais, codigopais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_paises);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        ImageView imgbuscarconsultapais = (ImageView) findViewById(R.id.imgbuscarconsultapais);
        Button btnactualizarconsultapais = (Button) findViewById(R.id.btnactualizarconsulta);
        Button btneliminarconsultapais = (Button) findViewById(R.id.btneliminarconsulta);

        id = (EditText) findViewById(R.id.txtconsultaidpais);
        nombrepais = (EditText) findViewById(R.id.txtconsultanombrepais);
        codigopais = (EditText) findViewById(R.id.txtconsultacodigopais);

        imgbuscarconsultapais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (id.length() > 0) {
                    BuscarConsultaPais();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar un id para realizar la busqueda",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnactualizarconsultapais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (id.length() > 0 && nombrepais.length() > 0 && codigopais.length() > 0) {
                    ActualizarConsultaPais();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar un id para realizar la busqueda y llenar todos los campos para actualizar el registro",Toast.LENGTH_LONG).show();
                }
            }

        });

        btneliminarconsultapais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (id.length() > 0 && nombrepais.length() > 0 && codigopais.length() > 0) {
                    EliminarConsultaPais();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar un id para realizar la busqueda y tener todos los campos para eliminar el registro",Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    private void BuscarConsultaPais() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = { id.getText().toString() };

        String [] fields = { Transacciones.nombrepais, Transacciones.codigopais };
        String wherecond = Transacciones.idpais + "=?";

        try {
            Cursor data = db.query(Transacciones.tablapaises, fields, wherecond, params, null, null, null);
            data.moveToFirst();

            nombrepais.setText(data.getString(0));
            codigopais.setText(data.getString(1));

            Toast.makeText(getApplicationContext(), "Consultado con exito",Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }

    }

    private void ActualizarConsultaPais() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = { id.getText().toString() };
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombrepais, nombrepais.getText().toString());
        valores.put(Transacciones.codigopais, codigopais.getText().toString());
        db.update(Transacciones.tablapaises, valores, Transacciones.idpais + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

    private void EliminarConsultaPais() {

        try {
            SQLiteDatabase db = conexion.getWritableDatabase();
            String [] params = { id.getText().toString() };
            String wherecond = Transacciones.idpais + "=?";
            db.delete(Transacciones.tablapaises, wherecond, params);
            Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
            ClearScreen();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "El registro no se puede eliminar porque el dato esta conectado a un contacto.", Toast.LENGTH_LONG).show();

        }
    }

    private void ClearScreen() {
        id.setText("");
        nombrepais.setText("");
        codigopais.setText("");
    }
}