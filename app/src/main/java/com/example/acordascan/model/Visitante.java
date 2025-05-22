package com.example.acordascan.model;

public class Visitante {
    private long id;
    private String nome;
    private String dataEvento;
    private String horarioEvento;
    private String nomeEvento;

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDataEvento() { return dataEvento; }
    public void setDataEvento(String dataEvento) { this.dataEvento = dataEvento; }

    public String getHorarioEvento() { return horarioEvento; }
    public void setHorarioEvento(String horarioEvento) { this.horarioEvento = horarioEvento; }

    public String getNomeEvento() { return nomeEvento; }
    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }
}

