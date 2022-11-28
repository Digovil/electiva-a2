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
import Utils.Apis;
import Utils.AutorServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createAutorActivity extends AppCompatActivity {

    AutorServices service;
    EditText txtNombre;
    EditText txtAdscription;
    EditText txtEmail;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity_autor);

        txtNombre=(EditText)findViewById(R.id.nombre);
        txtAdscription=(EditText)findViewById(R.id.adscripcion);
        txtEmail= (EditText) findViewById(R.id.email);
        btnSave=(Button)findViewById(R.id.OnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Autor autor =new Autor();
                autor.setNombre(txtNombre.getText().toString());
                autor.setAdscripcion(txtAdscription.getText().toString());
                autor.setEmail(txtEmail.getText().toString());

                if(txtNombre.length() == 0 || txtAdscription.length() == 0 || txtEmail.length() == 0){
                    Toast.makeText(createAutorActivity.this, "Todos los campos son necesarios ", Toast.LENGTH_LONG).show();
                }else{
                    addPersona(autor);
                }
            }
        });
    }

    public void addPersona(Autor autor) {
        service = Apis.getAutorService();

        Call<Autor> call = service.addAutor(autor);
        call.enqueue(new Callback<Autor>() {
            @Override
            public void onResponse(Call<Autor> call, Response<Autor> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(createAutorActivity.this, "Nota Guardada exitosamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(createAutorActivity.this, AutorActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(createAutorActivity.this, "Datos incorrectas", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Autor> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }
}
