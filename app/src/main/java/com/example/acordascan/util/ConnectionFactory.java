package com.example.acordascan.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionFactory extends SQLiteOpenHelper {

    public static final String DBNAME = "usuarios.db";

    public ConnectionFactory(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, senha TEXT)");
        db.execSQL("CREATE TABLE visitante(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, dataEvento TEXT, horarioEvento TEXT, nomeEvento TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS visitante");
        onCreate(db);
    }

    // Métodos de CRUD para "usuarios" mantêm-se iguais
    public boolean inserirUsuario(String nomeUs, String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("senha", senha);
        long result = db.insert("usuarios", null, values);
        db.close();
        return result != -1;
    }

    public boolean usuarioExiste(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ?", new String[]{email});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return existe;
    }

    public boolean verificarLogin(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ? AND senha = ?", new String[]{email, senha});
        boolean valido = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return valido;
    }
}





