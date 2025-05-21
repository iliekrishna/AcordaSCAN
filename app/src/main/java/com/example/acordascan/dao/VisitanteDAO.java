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
        conexao = new ConnectionFactory(context, "banco.db", null, 1);

        banco = conexao.getWritableDatabase();
    }

    //método inserir
    public Long insert(Visitante visitante) {
        ContentValues values = new ContentValues();
        values.put("nome", visitante.getNome());
        values.put("dataEvento", visitante.getDataEvento());
        values.put("horarioEvento", visitante.getHorarioEvento());
        values.put("nomeEvento", visitante.getNomeEvento());
        return (banco.insert("visitante", null, values));
    }


    public List<Visitante> obterTodos() {
        List<Visitante> visitantes = new ArrayList<>();
        Cursor cursor = banco.query("visitante", new String[]{"id", "nome", "dataEvento", "horarioEvento", "nomeEvento"},
                //era um args
                "nome=?", null, null, null, null);

        while (cursor.moveToNext()) {
            Visitante a = new Visitante();
            a.setId(cursor.getLong(0));
            a.setNome((cursor.getString(1)));
            a.setDataEvento(cursor.getString(2));
            a.setHorarioEvento(cursor.getString(3));
            a.setNomeEvento(cursor.getString(4));
            visitantes.add(a);
        }
        return visitantes;
    }

   /* public Visitante read(String no)*/
}


