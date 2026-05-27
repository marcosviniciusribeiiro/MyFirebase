package com.example.myfirebase;

public class Tarefa {
    private String id;
    private String titulo;

    public Tarefa(){}

    public Tarefa(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "id = " + id + " - titulo = " + titulo;
    }
}
