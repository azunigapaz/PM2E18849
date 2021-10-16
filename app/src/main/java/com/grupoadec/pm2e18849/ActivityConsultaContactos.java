package com.grupoadec.pm2e18849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.grupoadec.pm2e18849.Configuracion.SQLiteConexion;
import com.grupoadec.pm2e18849.Configuracion.Transacciones;
import com.grupoadec.pm2e18849.Tablas.Contactos;

import java.util.ArrayList;

public class ActivityConsultaContactos extends AppCompatActivity {

    // Variables globales
    SQLiteConexion conexion;
    ListView lvconsultacontactos;
    final ArrayList<Contactos> lista = new ArrayList<Contactos>();
    ArrayList<String> ArregloContactos;
    Contactos list_contactos = null;
    EditText txtbuscarcontactolista;
    ImageView imgreturnlc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_contactos);

        txtbuscarcontactolista = (EditText) findViewById(R.id.txtbuscarcontactolista);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        lvconsultacontactos = (ListView) findViewById(R.id.lvconsultacontactos);

        imgreturnlc = (ImageView) findViewById(R.id.imgreturnlc);

        ObtenerListaContactos();

        // Creamos el adapter
        ArrayAdapter adp =new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloContactos);
        lvconsultacontactos.setAdapter(adp);

        txtbuscarcontactolista.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adp.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgreturnlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        // evento para capturar los datos al seleccionar un item
        lvconsultacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contactos c = lista.get(i);
                //Toast.makeText(getApplicationContext(),"id: " + c.getIdcontacto() + " Nombre: " + c.getNombrecontacto() + " Numero: " + c.getNumerocontacto(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),ActivityAccionContacto.class);

                intent.putExtra("pxIdcontacto", c.getIdcontacto().toString());
                intent.putExtra("pxNombre", c.getNombrecontacto());
                intent.putExtra("pxNumero", c.getNumerocontacto().substring(5,c.getNumerocontacto().length()));
                intent.putExtra("pxNota", c.getNotacontacto());

                startActivity(intent);

            }
        });

    }

    private void ObtenerListaContactos() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Contactos list_contactos = null;
        //lista = new ArrayList<Contactos>();

        // creamos el cursor con el query
        Cursor cursor = db.rawQuery("SELECT A.idcontacto AS idcontacto, A.nombrecontacto AS nombrecontacto, B.codigopais AS codigopais, A.numerocontacto AS numerocontacto, A.notacontacto AS notacontacto, B.nombrepais AS nombrepais FROM " + Transacciones.tablacontactos + " AS A INNER JOIN " + Transacciones.tablapaises + " AS B ON A.idpais = B.idpais", null);

        // recorremos el cursor
        while(cursor.moveToNext()){
            list_contactos = new Contactos();
            list_contactos.setIdcontacto(cursor.getInt(0));
            list_contactos.setNombrecontacto(cursor.getString(1));
            list_contactos.setNumerocontacto("+" + cursor.getString(2) + " " + cursor.getString(3));
            list_contactos.setNotacontacto(cursor.getString(4));

            lista.add(list_contactos);

        }

        //cerramos el cursor
        cursor.close();

        filllist();
    }

    private void filllist() {

        ArregloContactos = new ArrayList<String>();
        for(int i = 0; i < lista.size(); i++){
            ArregloContactos.add(lista.get(i).getIdcontacto().toString() + " | " +
                    lista.get(i).getNombrecontacto() + " | " +
                    lista.get(i).getNumerocontacto() + " | " +
                    lista.get(i).getNotacontacto());
        }

    }

}