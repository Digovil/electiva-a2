package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Model.Articulo;
import Utils.Apis;
import Utils.ArticuloServices;
import adapter.ArticuloAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticuloActivity extends AppCompatActivity {
    ArticuloServices service;
    ArrayAdapter<Articulo> adapter;
    ListView listView;
    List<Articulo> listArticulo;
    FloatingActionButton btnAdd;
    ArrayList<Articulo> posiciones;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        listView =  (ListView) findViewById(R.id.revista_list);
        btnAdd=(FloatingActionButton)findViewById(R.id.AddRevista);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticuloActivity.this, createArticuloActivity.class);
                startActivity(intent);
            }
        });

        listArticulo();
    }

    public void listArticulo(){
        service = Apis.getArticuloService();
        Call<List<Articulo>> call = service.getArticulo();
        call.enqueue(
                new Callback<List<Articulo>>() {
                    @Override
                    public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                        listArticulo = response.body();
                        adapter = new ArticuloAdapter(ArticuloActivity.this,R.layout.activity_articulo_items,listArticulo);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        AppCompatImageView b = view.findViewById(R.id.btnDelete);
                                        AppCompatImageView update = view.findViewById(R.id.btnUpdate);

                                        b.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Articulo n = listArticulo.get(i);
                                                delete(n.getId(),i);
                                            }
                                        });

                                        update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent =new Intent(ArticuloActivity.this, updateArticulo.class);
                                                Articulo n = listArticulo.get(i);
                                                intent.putExtra("id", n.getId());
                                                intent.putExtra("titulo", n.getTitulo());
                                                intent.putExtra("paginainicio",n.getPaginainicio());
                                                intent.putExtra("paginafin",n.getPaginafin());
                                                intent.putExtra("idrevista",n.getIdrevista());
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Articulo>> call, Throwable t) {

                    }
                }
        );
    }

    public void delete(int id, int position){
        System.out.println(id);
        service = Apis.getArticuloService();
        Call<Articulo> call = service.deleteArticulo(id);
        call.enqueue(
                new Callback<Articulo>() {
                    @Override
                    public void onResponse(Call<Articulo> call, Response<Articulo> response) {
                        if (response.isSuccessful()) {
                            listArticulo.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Articulo> call, Throwable t) {

                    }
                }
        );
    }

}