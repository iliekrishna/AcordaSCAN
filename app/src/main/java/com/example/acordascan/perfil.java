package com.example.acordascan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class perfil extends AppCompatActivity {

    TextView txtUsuario;
    Button btnIngressos, btnSair;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnIngressos = findViewById(R.id.btnIngressos);
        btnSair = findViewById(R.id.btnSair);
        txtUsuario = findViewById(R.id.txtUsuario);

        SharedPreferences sharedPref = getSharedPreferences("usuario_logado", MODE_PRIVATE);
        String usuarioLogado = sharedPref.getString("Usuario", "Usuario não encontrado");

        txtUsuario.setText("Bem-vindo   " + usuarioLogado);

        btnIngressos.setOnClickListener(v ->{

            Intent intent = new Intent(perfil.this, ListaEventos.class);
            startActivity(intent);
            finish();

        });

        btnSair.setOnClickListener(v -> {

            // Limpa o SharedPreferences
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.apply();
            // Volta para tela de login
            Intent intent = new Intent(perfil.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // limpa pilha
            startActivity(intent);
            //finish();
        });


        }

    }



