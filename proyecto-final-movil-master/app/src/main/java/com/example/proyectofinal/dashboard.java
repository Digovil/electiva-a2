package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class dashboard extends AppCompatActivity {

    Button btnNotas;
    Button btnRevista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnNotas = (Button) findViewById(R.id.btnAutor);
        btnRevista = (Button) findViewById(R.id.btnRevista);

        btnNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, AutorActivity.class);
                startActivity(intent);
            }
        });

        btnRevista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,  RevistaActivity.class);
                startActivity(intent);
            }
        });


    }

}