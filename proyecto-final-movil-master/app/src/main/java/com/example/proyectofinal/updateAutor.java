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

public class updateAutor extends AppCompatActivity {

    AutorServices service;
    EditText txtNombre;
    EditText txtAdscription;
    EditText txtEmail;
    Button btnSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_autor);

        Bundle extras = getIntent().getExtras();
        final int Id = extras.getInt("id");
        final String nombre = extras.getString("nombre");
        final String adscription = extras.getString("adscription");
        final String email = extras.getString("email");

        txtNombre=(EditText)findViewById(R.id.nombreup);
        txtAdscription=(EditText)findViewById(R.id.adscripcionup);
        txtEmail= (EditText) findViewById(R.id.emailup);
        btnSave=(Button)findViewById(R.id.OnUpdate);

        txtNombre.setText(nombre);
        txtAdscription.setText(adscription);
        txtEmail.setText(email);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Autor autor = new Autor();

                autor.setAdscripcion(txtAdscription.getText().toString());
                autor.setNombre(txtNombre.getText().toString());
                autor.setEmail(txtEmail.getText().toString());
                updateAutor(autor,Id);
            }
        });
    }

    public void updateAutor(Autor p, int id){
        service= Apis.getAutorService();
        Call<Autor> call=service.updateAutor(p,id);
        call.enqueue(new Callback<Autor>() {
            @Override
            public void onResponse(Call<Autor> call, Response<Autor> response) {
                if(response.isSuccessful()){
                    Toast.makeText(updateAutor.this,"Se Actualizó con éxito",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(updateAutor.this, AutorActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Autor> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}
