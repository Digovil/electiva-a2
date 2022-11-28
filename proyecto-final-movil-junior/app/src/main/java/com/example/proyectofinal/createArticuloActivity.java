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

import Model.Articulo;
import Utils.Apis;
import Utils.ArticuloServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createArticuloActivity extends AppCompatActivity {

    ArticuloServices service;
    EditText txtTitulo2;
    EditText txtPaginainicio;
    EditText txtPaginafin;
    EditText txtIdrevista;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity_articulo);

        txtTitulo2=(EditText)findViewById(R.id.titulo2);
        txtPaginainicio=(EditText)findViewById(R.id.paginainicio);
        txtPaginafin= (EditText) findViewById(R.id.paginafin);
        txtIdrevista= (EditText) findViewById(R.id.idrevista);
        btnSave=(Button)findViewById(R.id.OnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Articulo articulo =new Articulo();
                articulo.setTitulo(txtTitulo2.getText().toString());
                articulo.setPaginainicio(Integer.parseInt(txtPaginainicio.getText().toString()));
                articulo.setPaginafin(Integer.parseInt(txtPaginafin.getText().toString()));
                articulo.setIdrevista(Integer.parseInt(txtIdrevista.getText().toString()));

                if(txtTitulo2.length() == 0 || txtPaginainicio.length() == 0 || txtPaginafin.length() == 0 || txtIdrevista.length() == 0 ){
                    Toast.makeText(createArticuloActivity.this, "Todos los campos son necesarios ", Toast.LENGTH_LONG).show();
                }else{
                    addArticulo(articulo);
                }
            }
        });
    }

    public void addArticulo(Articulo articulo) {
        service = Apis.getArticuloService();

        Call<Articulo> call = service.addArticulo(articulo);
        call.enqueue(new Callback<Articulo>() {
            @Override
            public void onResponse(Call<Articulo> call, Response<Articulo> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(createArticuloActivity.this, "Nota Guardada exitosamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(createArticuloActivity.this, ArticuloActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(createArticuloActivity.this, "Datos incorrectas", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Articulo> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }
}
