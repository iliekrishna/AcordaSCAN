package com.example.acordascan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.acordascan.util.ConnectionFactory;
import com.example.acordascan.model.Visitante;

import java.util.ArrayList;
import java.util.List;

public class VisitanteDAO {

    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    public VisitanteDAO(Context context) {
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    // CREATE
    public long insert(Visitante visitante) {
        ContentValues values = new ContentValues();
        values.put("nome", visitante.getNome());
        values.put("dataEvento", visitante.getDataEvento());
        values.put("horarioEvento", visitante.getHorarioEvento());
        values.put("nomeEvento", visitante.getNomeEvento());

        long result = banco.insert("visitante", null, values);
        return result;
    }

    // READ ALL
    public List<Visitante> obterTodos() {
        List<Visitante> visitantes = new ArrayList<>();
        Cursor cursor = banco.query("visitante",
                new String[]{"id", "nome", "dataEvento", "horarioEvento", "nomeEvento"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Visitante visitante = new Visitante();
            visitante.setId(cursor.getLong(0));
            visitante.setNome(cursor.getString(1));
            visitante.setDataEvento(cursor.getString(2));
            visitante.setHorarioEvento(cursor.getString(3));
            visitante.setNomeEvento(cursor.getString(4));
            visitantes.add(visitante);
        }
        cursor.close();
        return visitantes;
    }

    // READ by ID
    public Visitante readById(long id) {
        Cursor cursor = banco.query("visitante",
                new String[]{"id", "nome", "dataEvento", "horarioEvento", "nomeEvento"},
                "id = ?", new String[]{String.valueOf(id)},
                null, null, null);

        Visitante visitante = null;
        if (cursor.moveToFirst()) {
            visitante = new Visitante();
            visitante.setId(cursor.getLong(0));
            visitante.setNome(cursor.getString(1));
            visitante.setDataEvento(cursor.getString(2));
            visitante.setHorarioEvento(cursor.getString(3));
            visitante.setNomeEvento(cursor.getString(4));
        }
        cursor.close();
        return visitante;
    }

    // UPDATE
    public int update(Visitante visitante) {
        ContentValues values = new ContentValues();
        values.put("nome", visitante.getNome());
        values.put("dataEvento", visitante.getDataEvento());
        values.put("horarioEvento", visitante.getHorarioEvento());
        values.put("nomeEvento", visitante.getNomeEvento());

        return banco.update("visitante", values, "id = ?", new String[]{String.valueOf(visitante.getId())});
    }

    // DELETE
    public int delete(long id) {
        return banco.delete("visitante", "id = ?", new String[]{String.valueOf(id)});
    }
}



