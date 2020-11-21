package com.example.lv1_baksaj;

public class Student {

    private String Ime, Prezime, Predmet;

    public Student(String ime, String prezime, String predmet){
        Ime = ime;
        Prezime = prezime;
        Predmet = predmet;
    }

    public String getIme() {
        return Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public String getPredmet() {
        return Predmet;
    }
}
