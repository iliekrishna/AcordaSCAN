package com.example.acordascan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.acordascan.dao.VisitanteDAO;
import com.example.acordascan.model.Visitante;
import com.example.acordascan.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListaEventos extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> qrCodes;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        listView = findViewById(R.id.lsvDados);
        dbHelper = new DBHelper(this);

        // Exemplo de inserção de dados
         dbHelper.inserirQRCode("https://meusite.com");
         dbHelper.inserirQRCode("https://outrosite.com");

        // Pegando dados do banco
        qrCodes = dbHelper.listarQRCodes();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                qrCodes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = qrCodes.get(position);
            Toast.makeText(ListaEventos.this, "Selecionado: " + item,
                    Toast.LENGTH_SHORT).show();
        });
    }
}
