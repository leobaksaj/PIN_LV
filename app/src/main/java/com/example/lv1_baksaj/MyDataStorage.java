package com.example.lv1_baksaj;

import java.util.ArrayList;
import java.util.List;

public class MyDataStorage {

    private List<Object> students;
    private MyDataStorage(){
        students = new ArrayList<Object>();
    }
    static private MyDataStorage instanca;

    public static MyDataStorage getInstanca() {
        if(instanca == null){
            instanca = new MyDataStorage();
            instanca.students.add("Studenti");
        }
        return instanca;
    }

    public void setStudenti(Student studenti) {
        this.students.add(studenti);
    }

    public List<Object> getStudenti(){
        return students;
    }
}
