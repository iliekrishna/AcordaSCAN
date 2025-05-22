package com.example.acordascan.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "qrcodes.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE qrdata (id INTEGER PRIMARY KEY AUTOINCREMENT, conteudo TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS qrdata");
        onCreate(db);
    }

    // Inserção
    public void inserirQRCode(String conteudo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("conteudo", conteudo);
        db.insert("qrdata", null, valores);
        db.close();
    }

    // Novo: Listar QRCodes
    public List<String> listarQRCodes() {
        List<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("qrdata",
                new String[]{"id", "conteudo"},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String conteudo = cursor.getString(cursor.getColumnIndexOrThrow("conteudo"));
                lista.add("ID: " + id + " - Conteúdo: " + conteudo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
}
