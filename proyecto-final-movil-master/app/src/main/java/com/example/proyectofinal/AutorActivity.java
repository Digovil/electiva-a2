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

import Model.Autor;
import Utils.Apis;
import Utils.AutorServices;
import adapter.AutorAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutorActivity extends AppCompatActivity {
    AutorServices service;
    ArrayAdapter<Autor> adapter;
    ListView listView;
    List<Autor> listAutor;
    FloatingActionButton btnAdd;
    ArrayList<Autor> posiciones;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        listView =  (ListView) findViewById(R.id.revista_list);
        btnAdd=(FloatingActionButton)findViewById(R.id.AddRevista);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AutorActivity.this, createAutorActivity.class);
                startActivity(intent);
            }
        });

        listAutor();
    }

    public void listAutor(){
        service = Apis.getAutorService();
        Call<List<Autor>> call = service.getAutor();
        call.enqueue(
                new Callback<List<Autor>>() {
                    @Override
                    public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                        listAutor = response.body();
                        adapter = new  AutorAdapter(AutorActivity.this,R.layout.activity_autor_items,listAutor);
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
                                                Autor n = listAutor.get(i);
                                                System.out.println(n.getNombre());
                                                delete(n.getId(),i);
                                            }
                                        });

                                        update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent =new Intent(AutorActivity.this, updateAutor.class);
                                                Autor n = listAutor.get(i);
                                                intent.putExtra("id", n.getId());
                                                intent.putExtra("nombre", n.getNombre());
                                                intent.putExtra("adscription",n.getAdscripcion());
                                                intent.putExtra("email",n.getEmail());
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Autor>> call, Throwable t) {

                    }
                }
        );
    }

    public void delete(int id, int position){
        System.out.println(id);
        service = Apis.getAutorService();
        Call<Autor> call = service.deleteAutor(id);
        call.enqueue(
                new Callback<Autor>() {
                    @Override
                    public void onResponse(Call<Autor> call, Response<Autor> response) {
                        if (response.isSuccessful()) {
                            listAutor.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Autor> call, Throwable t) {

                    }
                }
        );
    }

}