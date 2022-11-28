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
import Utils.AutorServices;
import Utils.RevistaServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createRevistaActivity extends AppCompatActivity {

    RevistaServices service;
    EditText txtNumero;
    EditText txtTitulo;
    EditText txtAnio;
    EditText txtIssn;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_revista);

        txtNumero=(EditText)findViewById(R.id.numero);
        txtTitulo=(EditText)findViewById(R.id.titulo);
        txtAnio = (EditText) findViewById(R.id.anio);
        txtIssn = (EditText) findViewById(R.id.issn);
        btnSave=(Button)findViewById(R.id.OnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Revista revista = new Revista();
                revista.setNumero(Integer.parseInt(txtNumero.getText().toString()));
                revista.setTitulo(txtTitulo.getText().toString());
                revista.setAnio(txtAnio.getText().toString());
                revista.setIssn(txtIssn.getText().toString());

                if(txtNumero.length() == 0 || txtTitulo.length() == 0 || txtAnio.length() == 0 || txtIssn.length() == 0){
                    Toast.makeText(createRevistaActivity.this, "Todos los campos son necesarios ", Toast.LENGTH_LONG).show();
                }else{
                    addRevista(revista);
                }
            }
        });
    }

    public void addRevista(Revista revista) {
        service = Apis.getRevistaService();

        Call<Revista> call = service.addRevista(revista);
        call.enqueue(new Callback<Revista>() {
            @Override
            public void onResponse(Call<Revista> call, Response<Revista> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(createRevistaActivity.this, "Nota Guardada exitosamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(createRevistaActivity.this, RevistaActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(createRevistaActivity.this, "Datos incorrectas", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Revista> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }
}
