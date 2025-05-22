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
    private List<String> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        listView = findViewById(R.id.lsvDados);

        // Exemplo com dados mockados - substitua pelos seus dados reais
        eventos = new ArrayList<>();
        eventos.add("Evento 1 - 10/10/2023");
        eventos.add("Evento 2 - 15/10/2023");
        eventos.add("Evento 3 - 20/10/2023");

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, eventos);
        listView.setAdapter(adapter);

        // Clique nos itens
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = eventos.get(position);
            Toast.makeText(ListaEventos.this, "Selecionado: " + item,
                    Toast.LENGTH_SHORT).show();
        });
    }
}