package com.example.myapplication2.datamodels;

public class Restaurant {
    private String nome, indirizzo;
    private long numeroTelefonico;

    public Restaurant(String nome, String indirizzo, long numeroTelefonico){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getNome() {
        return nome;
    }

    public long getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroTelefonico(long numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

}

