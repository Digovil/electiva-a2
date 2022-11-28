package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Model.Autor;
import Model.Revista;
import Utils.Apis;
import Utils.RevistaServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateRevista extends AppCompatActivity {

    RevistaServices service;
    EditText txtNumero;
    EditText txtTitulo;
    EditText txtAnio;
    EditText txtIssn;
    Button btnSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_revista);

        Bundle extras = getIntent().getExtras();
        final int Id = extras.getInt("id");
        final int numero = extras.getInt("numero");
        final String titulo = extras.getString("titulo");
        final String anio = extras.getString("anio");
        final String issn = extras.getString("issn");

        txtNumero=(EditText)findViewById(R.id.numeroup);
        txtTitulo=(EditText)findViewById(R.id.tituloup);
        txtAnio = (EditText) findViewById(R.id.anioup);
        txtIssn = (EditText) findViewById(R.id.issnup);
        btnSave=(Button)findViewById(R.id.OnUpdate);

        txtNumero.setText(Integer.toString(numero));
        txtTitulo.setText(titulo);
        txtAnio.setText(anio);
        txtIssn.setText(issn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Revista revista = new Revista();

                revista.setNumero(Integer.parseInt(txtNumero.getText().toString()));
                revista.setTitulo(txtTitulo.getText().toString());
                revista.setAnio(txtAnio.getText().toString());
                revista.setIssn(txtIssn.getText().toString());
                updateRevista(revista,Id);
            }
        });
    }

    public void updateRevista(Revista p, int id){
        service= Apis.getRevistaService();
        Call<Revista> call=service.updateRevista(p,id);
        call.enqueue(new Callback<Revista>() {
            @Override
            public void onResponse(Call<Revista> call, Response<Revista> response) {
                if(response.isSuccessful()){
                    Toast.makeText(updateRevista.this,"Se Actualizó con éxito",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(updateRevista.this, RevistaActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Revista> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}
