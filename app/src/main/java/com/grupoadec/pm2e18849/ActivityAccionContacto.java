package com.grupoadec.pm2e18849;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.grupoadec.pm2e18849.Configuracion.SQLiteConexion;
import com.grupoadec.pm2e18849.Configuracion.Transacciones;

public class ActivityAccionContacto extends AppCompatActivity {

    // variables globales
    static final int REQUEST_PHONE_CALL = 1;
    static final int PETICION_PHONE_CALL = 101;

    AlertDialog.Builder builder;

    SQLiteConexion conexion;
    EditText txtconsultaidcontacto, txtconsultanombrecontacto, txtconsultacelcontacto, txtconsultanotacontacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accion_contacto);

        conexion = new SQLiteConexion(this,Transacciones.NameDatabase,null,1);

        ImageView imgreturnic = (ImageView) findViewById(R.id.imgreturnic);

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

        builder = new AlertDialog.Builder(this);

        imgreturnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityConsultaContactos.class);
                startActivity(intent);
            }
        });

        btnllamarcontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtconsultaidcontacto.length() > 0 && txtconsultanombrecontacto.length() > 0 && txtconsultacelcontacto.length() > 0 && txtconsultanotacontacto.length() > 0) {

                    //Mensaje de dialogo
                    builder.setMessage("Desea llamar a " + txtconsultanombrecontacto.getText())
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    try{
                                        Permisos();
                                    }catch (Exception ex){
                                        ex.toString();
                                    }

                                    Toast.makeText(getApplicationContext(),"Realizando llamada",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"No se realizo la llamada",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Alerta");
                    alert.show();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe de llenar todos los campos para realizar la llamada",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnactualizarconsultacontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtconsultaidcontacto.length() > 0 && txtconsultanombrecontacto.length() > 0 && txtconsultacelcontacto.length() > 0 && txtconsultanotacontacto.length() > 0) {

                    //Mensaje de dialogo
                    builder.setMessage("Desea actualizar el registro ?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ActualizarConsultaContacto();
                                    Toast.makeText(getApplicationContext(),"Registro Actualizado",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"No se actualizo el registro",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Alerta");
                    alert.show();

                } else {
                    Toast.makeText(getApplicationContext(), "Debe de llenar todos los campos para actualizar el contacto",Toast.LENGTH_LONG).show();
                }
            }
        });

        btneliminarconsultacontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtconsultaidcontacto.length() > 0 && txtconsultanombrecontacto.length() > 0 && txtconsultacelcontacto.length() > 0 && txtconsultanotacontacto.length() > 0) {

                    builder.setMessage("Desea eliminar el registro ?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    EliminarConsultaContacto();
                                    Toast.makeText(getApplicationContext(),"Registro Eliminado",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"No se elimino el registro",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Alerta");
                    alert.show();

                } else {
                    Toast.makeText(getApplicationContext(), "Debe de llenar todos los campos para eliminar el registro completo",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void Permisos() {
        // valido si el permiso para acceder a la telefono esta otorgado
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            // Otorgamos el permiso para acceder al telefono
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, PETICION_PHONE_CALL);
        }else{
            LlamarContacto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int RequestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(RequestCode, permissions, grantResults);

        if(RequestCode == REQUEST_PHONE_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                LlamarContacto();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Se necesita permisos para llamadas", Toast.LENGTH_LONG).show();
        }
    }

    private void LlamarContacto() {
        String number = txtconsultacelcontacto.getText().toString();

        /*
        Intent callintent = new Intent(Intent.ACTION_CALL);
        callintent.setData(Uri.parse("tel:" + number));
        startActivity(callintent);
        */
        Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));
        startActivity(intent);

    }

    private void ActualizarConsultaContacto() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = { txtconsultaidcontacto.getText().toString() };

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombrecontacto, txtconsultanombrecontacto.getText().toString());
        valores.put(Transacciones.numerocontacto, txtconsultacelcontacto.getText().toString());
        valores.put(Transacciones.notacontacto, txtconsultanotacontacto.getText().toString());

        db.update(Transacciones.tablacontactos, valores, Transacciones.idcontacto + "=?", params);
        //Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_LONG).show();

        ClearScreen();

        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityConsultaContactos.class);
        startActivity(intent);
    }

    private void EliminarConsultaContacto() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = { txtconsultaidcontacto.getText().toString() };

        String wherecond = Transacciones.idcontacto + "=?";

        db.delete(Transacciones.tablacontactos, wherecond, params);

        //Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        ClearScreen();

        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityConsultaContactos.class);
        startActivity(intent);

    }

    private void ClearScreen() {

        txtconsultaidcontacto.setText("");
        txtconsultanombrecontacto.setText("");
        txtconsultacelcontacto.setText("");
        txtconsultanotacontacto.setText("");

    }

}