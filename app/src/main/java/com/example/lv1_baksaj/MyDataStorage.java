package com.example.lv1_baksaj;

import java.util.ArrayList;
import java.util.List;

public class MyDataStorage {

    private List<Object> studenti;
    private MyDataStorage(){
        studenti = new ArrayList<Object>();
    }
    static private MyDataStorage instanca;

    public static MyDataStorage getInstanca() {
        if(instanca == null){
            instanca = new MyDataStorage();
            instanca.studenti.add("Studenti");
        }
        return instanca;
    }

    public void setStudenti(Student studenti) {
        this.studenti.add(studenti);
    }

    public List<Object> getStudenti(){
        return studenti;
    }
}
