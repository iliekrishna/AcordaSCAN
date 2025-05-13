package com.example.acordascan;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Gerador extends AppCompatActivity {

    private Button btnCriar;
    private EditText edtNome, edtData, edtHora;

    private ImageView imgQrcode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gerador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCriar = findViewById(R.id.btnCriar);
        edtNome = findViewById(R.id.edtNome);
        edtData = findViewById(R.id.edtData);
        edtHora = findViewById(R.id.edtHora);
        imgQrcode = findViewById(R.id.imgQrCode);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateQRCode();
            }
        });
    }

    private void generateQRCode() {
        String eventName = edtNome.getText().toString().trim();
        String eventDate = edtData.getText().toString().trim();
        String eventTime = edtHora.toString().trim();

        if (eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String qrContent = null;
        try {
            qrContent = "EVENTO:" + eventName + "\n" +
                    "DATA:" + eventDate + "\n" +
                    "HORA:" + eventTime;

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(qrContent, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

            imgQrcode.setImageBitmap(bitmap);
            imgQrcode.setVisibility(View.VISIBLE);
        } catch (Exception e) { // Captura qualquer exceção, não só WriterException
            e.printStackTrace();
            Toast.makeText(this, "Erro ao gerar QR Code: " + e.getMessage(), Toast.LENGTH_LONG).show();

            // Log detalhado para debug
            Log.e("QRCodeError", "Conteúdo do QR: " + qrContent);
            Log.e("QRCodeError", "Stack trace: ", e);
        }
    }
    private boolean isValidDate(String date) {
        String pattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$";
        return date.matches(pattern);
    }
}