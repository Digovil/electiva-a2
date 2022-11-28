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

public class updateArticulo extends AppCompatActivity {

    ArticuloServices service;
    EditText txtTitulo2;
    EditText txtPaginainicio;
    EditText txtPaginafin;
    EditText txtIdrevista;
    Button btnSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_articulo);

        Bundle extras = getIntent().getExtras();
        final int Id = extras.getInt("id");
        final String titulo = extras.getString("titulo");
        final int paginainicio = extras.getInt("paginainicio");
        final int paginafin = extras.getInt("paginafin");
        final int idrevista = extras.getInt("idrevista");

        txtTitulo2=(EditText)findViewById(R.id.titulo2up);
        txtPaginainicio=(EditText)findViewById(R.id.paginainicioup);
        txtPaginafin= (EditText) findViewById(R.id.paginafinup);
        txtIdrevista= (EditText) findViewById(R.id.idrevistaup);
        btnSave=(Button)findViewById(R.id.OnUpdate);

        txtTitulo2.setText(titulo);
        txtPaginainicio.setText(Integer.toString(paginainicio));
        txtPaginafin.setText(Integer.toString(paginafin));
        txtIdrevista.setText(Integer.toString(idrevista));


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Articulo articulo = new Articulo();

                articulo.setTitulo(txtTitulo2.getText().toString());
                articulo.setPaginainicio(Integer.parseInt(txtPaginainicio.getText().toString()));
                articulo.setPaginafin(Integer.parseInt(txtPaginafin.getText().toString()));
                articulo.setIdrevista(Integer.parseInt(txtIdrevista.getText().toString()));
                updateArticulo(articulo,Id);
            }
        });
    }

    public void updateArticulo(Articulo p, int id){
        service= Apis.getArticuloService();
        Call<Articulo> call=service.updateArticulo(p,id);
        call.enqueue(new Callback<Articulo>() {
            @Override
            public void onResponse(Call<Articulo> call, Response<Articulo> response) {
                if(response.isSuccessful()){
                    Toast.makeText(updateArticulo.this,"Se Actualizó con éxito",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(updateArticulo.this, ArticuloActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Articulo> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}
