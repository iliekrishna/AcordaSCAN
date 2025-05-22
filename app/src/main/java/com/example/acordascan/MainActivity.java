package com.example.acordascan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.acordascan.util.ConnectionFactory;

public class MainActivity extends AppCompatActivity {

    Button btnEntrar;
    EditText edtEmail, edtSenhaLogin;

    ConnectionFactory db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new ConnectionFactory(this);
        btnEntrar = findViewById(R.id.btnEntrar);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenhaLogin = findViewById(R.id.edtSenhaLogin);
    }

    public void cadastrar (View view){

        Intent intent = new Intent(MainActivity.this, senhadef.class);
        startActivity(intent);
        finish();

    }

    public void Login (View view){
        String email = edtEmail.getText().toString();
        String senha = edtSenhaLogin.getText().toString();

        if (db.verificarLogin(email, senha)) {

            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, QrCode.class);
            startActivity(intent);
            finish();
            // Redirecionar para outra tela
        } else {
            Toast.makeText(this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
        }


    }


}