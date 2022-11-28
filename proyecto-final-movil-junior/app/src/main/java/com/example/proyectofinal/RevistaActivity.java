package com.example.proyectofinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Model.Revista;
import Utils.Apis;
import Utils.RevistaServices;
import adapter.RevistaAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevistaActivity extends AppCompatActivity {
    RevistaServices service;
    ArrayAdapter<Revista> adapter;
    ListView listView;
    List<Revista> listRevista;
    FloatingActionButton btnAdd;
    ArrayList<Revista> posiciones;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revista);

        listView =  (ListView) findViewById(R.id.revista_list);
        btnAdd=(FloatingActionButton)findViewById(R.id.AddRevista);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RevistaActivity.this, createRevistaActivity.class);
                startActivity(intent);
            }
        });

        listRevista();
    }

    public void listRevista(){
        service = Apis.getRevistaService();
        Call<List<Revista>> call = service.getRevista();


        call.enqueue(
                new Callback<List<Revista>>() {
                    @Override
                    public void onResponse(Call<List<Revista>> call, Response<List<Revista>> response) {
                        listRevista = response.body();
                        adapter = new  RevistaAdapter(RevistaActivity.this,R.layout.activity_revista_items,listRevista);
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
                                                Revista n = listRevista.get(i);
                                                delete(n.getId(),i);
                                            }
                                        });

                                        update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent =new Intent(RevistaActivity.this, updateRevista.class);
                                                Revista n = listRevista.get(i);
                                                intent.putExtra("id", n.getId());
                                                intent.putExtra("titulo", n.getTitulo());
                                                intent.putExtra("issn",n.getIssn());
                                                intent.putExtra("numero",n.getNumero());
                                                intent.putExtra("anio",n.getAnio());
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Revista>> call, Throwable t) {

                    }
                }
        );
    }

    public void delete(int id, int position){
        System.out.println(id);
        service = Apis.getRevistaService();
        Call<Revista> call = service.deleteRevista(id);
        call.enqueue(
                new Callback<Revista>() {
                    @Override
                    public void onResponse(Call<Revista> call, Response<Revista> response) {
                        if (response.isSuccessful()) {
                            listRevista.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Revista> call, Throwable t) {

                    }
                }
        );
    }

}