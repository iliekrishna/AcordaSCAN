package com.example.acordascan;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.acordascan.dao.VisitanteDAO;
import com.example.acordascan.model.Visitante;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class QrCode extends AppCompatActivity {

    VisitanteDAO visitanteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        visitanteDAO = new VisitanteDAO(this);
    }

    public void perfil(View view){
        Intent intent = new Intent(QrCode.this, perfil.class);
        startActivity(intent);
        finish();
    }

    public void Criar(View view){
        Intent intent = new Intent(QrCode.this, Gerador.class);
        startActivity(intent);
        finish();
    }
    public void Scannear(View view){
        ScannerCod();
    }

    public void ScannerCod(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Aumente o volume para utilizar o flash");
        options.setBeepEnabled(false);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null) {
            String conteudo = result.getContents();
            Visitante visitante = converterConteudoParaVisitante(conteudo);

            if (visitante != null) {
                // Recuperar nome logado
                SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
                String nomeLogado = prefs.getString("nome", "Desconhecido");

                visitante.setNome(nomeLogado);

                long id = visitanteDAO.insert(visitante);
                Toast.makeText(QrCode.this, "Visitante salvo com ID: " + id, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QrCode.this, "Formato inválido do QR Code", Toast.LENGTH_SHORT).show();
            }
        }
    });

    private Visitante converterConteudoParaVisitante(String conteudo) {
        try {
            String[] partes = conteudo.split(",");
            if (partes.length >= 3) {
                Visitante visitante = new Visitante();
                // NÃO definir nome aqui
                visitante.setDataEvento(partes[0].trim());
                visitante.setHorarioEvento(partes[1].trim());
                visitante.setNomeEvento(partes[2].trim());
                return visitante;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

