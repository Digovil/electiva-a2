package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import Model.Usuario;
import Utils.Apis;
import Utils.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    UsuarioService service;

    TextView txtEmail;
    EditText txtPassword;
    Button btnSave;

    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         txtEmail=(EditText)findViewById(R.id.email);
         txtPassword=(EditText)findViewById(R.id.password);
         btnSave=(Button)findViewById(R.id.btnSave);

         register=(TextView)findViewById(R.id.registrarme);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user=new Usuario();
                user.setCorreo(txtEmail.getText().toString());
                user.setClave(txtPassword.getText().toString());

                if(txtEmail.length() == 0 || txtPassword.length() == 0){
                    Toast.makeText(MainActivity.this, "Todos los campos son necesarios ", Toast.LENGTH_LONG).show();
                }else{
                    loginUser(user);
                }
            }
        });
    }

    public void loginUser(Usuario user) {
        service = Apis.getUsuarioService();

        Call<Usuario> call = service.setLogin(user);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }
}