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

import java.util.List;

public class ListaEventos extends AppCompatActivity {

    private ListView lsvDados;
    private VisitanteDAO visitanteDAO;
    private List<Visitante> visitantes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_eventos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

            /*updateListView();*/
        });

        visitanteDAO = new VisitanteDAO(this);

        visitantes = visitanteDAO.obterTodos();

      /*  if(visitantes != null && !visitantes.isEmpty()) {
            lsvDados
        }*/


        lsvDados = findViewById(R.id.lsvDados);

    }

    public void mostrarTexto() {
        Toast.makeText(getApplicationContext(), "Novo!!!", Toast.LENGTH_LONG).show();
    }

    public void updateListView() {
        visitanteDAO = new VisitanteDAO(this);
        visitantes = visitanteDAO.obterTodos(); // Fetching all students

        if (visitantes != null && !visitantes.isEmpty()) {
            lsvDados = findViewById(R.id.lsvDados);
            ArrayAdapter<Visitante> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, visitantes);
            lsvDados.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Nenhum visitante encontrado!", Toast.LENGTH_SHORT).show();
            lsvDados = findViewById(R.id.lsvDados);
            ArrayAdapter<Visitante> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, visitantes);
            lsvDados.setAdapter(adapter);
        }
    }

    public void updateListView(View view) {
        visitanteDAO = new VisitanteDAO(this);
        visitantes = visitanteDAO.obterTodos(); // Fetching all students

        if (visitantes != null && !visitantes.isEmpty()) {
            lsvDados = findViewById(R.id.lsvDados);
            ArrayAdapter<Visitante> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, visitantes);
            lsvDados.setAdapter(adapter);
        } else {
            Toast.makeText(ListaEventos.this, "Nenhum visitante encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            updateListView(); // Refresh the list after deletion
        }
    }
}