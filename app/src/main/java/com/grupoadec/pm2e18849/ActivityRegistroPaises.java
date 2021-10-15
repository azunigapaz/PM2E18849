package com.grupoadec.pm2e18849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityRegistroPaises extends AppCompatActivity {

    Button btnconsultapaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paises);

        btnconsultapaises = (Button) findViewById(R.id.btnconsultapaises);

        btnconsultapaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityConsultaPaises.class);
                startActivity(intent);
            }
        });
    }
}