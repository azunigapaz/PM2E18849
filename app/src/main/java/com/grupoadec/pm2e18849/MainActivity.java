package com.grupoadec.pm2e18849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.grupoadec.pm2e18849.Configuracion.SQLiteConexion;
import com.grupoadec.pm2e18849.Configuracion.Transacciones;
import com.grupoadec.pm2e18849.Tablas.Paises;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declaracion de variables globales
    SQLiteConexion conexion;

    ImageView imgagregarpais;

    Spinner cmbpais;
    EditText txtnombrecontacto;
    EditText txtnumerocontacto;
    EditText txtnotacontacto;
    Button btnagregarcontacto;
    Button btnlistacontactos;

    ArrayList<String> listapaises;
    ArrayList<Paises> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* inicializamos las variables */
        // Conexion
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        // imageView
        imgagregarpais = (ImageView) findViewById(R.id.imgagregarpais);
        // Spinner
        cmbpais = (Spinner) findViewById(R.id.cmbpais);
        // EditText
        txtnombrecontacto = (EditText) findViewById(R.id.txtnombrecontacto);
        txtnumerocontacto = (EditText) findViewById(R.id.txtnumerocontacto);
        txtnotacontacto = (EditText) findViewById(R.id.txtnotacontacto);
        // Button
        btnagregarcontacto = (Button) findViewById(R.id.btnagregarcontacto);
        btnlistacontactos = (Button) findViewById(R.id.btnlistacontactos);

        // Accion agregar paises
        imgagregarpais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityRegistroPaises.class);
                startActivity(intent);
            }
        });

        ObtenerListaPaises();

        // Creamos el Array Adapter
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listapaises);
        cmbpais.setAdapter(adp);

    }

    private void ObtenerListaPaises() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Paises list_paises = null;
        lista = new ArrayList<Paises>();

        // Creamos el cursor para recorrer la tabla paises
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablapaises, null);

        // recorremos la informacion
        while (cursor.moveToNext()){
            list_paises = new Paises();
            list_paises.setId(cursor.getInt(0));
            list_paises.setNombrepais(cursor.getString(1));
            list_paises.setCodigopais(cursor.getString(2));

            lista.add(list_paises);
        }

        // cerramos el cursor
        cursor.close();

        fillcombo();

    }

    private void fillcombo() {
        listapaises = new ArrayList<String>();
        for(int i = 0; i < lista.size(); i++){
            listapaises.add(lista.get(i).getId().toString() + " | " +
                    lista.get(i).getNombrepais() + " | " +
                    lista.get(i).getCodigopais());
        }
    }
}