package com.example.lv1_baksaj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lv1_baksaj.PocetniActivity;
import com.example.lv1_baksaj.R;

public class PageFragment3 extends Fragment {

    private String sIme;
    private String sPrezime;
    private String sDatum;
    private String sPredmet;
    private String sProfesor;
    private String sAkGod;
    private String sSatiPredavanja;
    private String sSatiLva;

    private TextView oIme;
    private TextView oPrezime;
    private TextView oDatum;
    private TextView oPredmet;
    private TextView oProfesor;
    private TextView oAkGod;
    private TextView oSatiPredavanja;
    private TextView oSatiLva;
    private Button oSumButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.summary_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oIme = view.findViewById(R.id.textView12);
        oPrezime = view.findViewById(R.id.textView13);
        oDatum = view.findViewById(R.id.textView14);
        oPredmet = view.findViewById(R.id.textView23);
        oProfesor = view.findViewById(R.id.textView16);
        oAkGod = view.findViewById(R.id.textView22);
        oSatiPredavanja = view.findViewById(R.id.textView19);
        oSatiLva = view.findViewById(R.id.textView20);
        oSumButton = view.findViewById(R.id.button4);

        oSumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PocetniActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void updatePersonalInfo(String ime, String prezime, String datum){
        sIme = ime;
        sPrezime = prezime;

        oIme.setText(ime);
        oPrezime.setText(prezime);
        oDatum.setText(datum);
    }
    
    public void updateStudentInfo(String predmet, String ime_profesora, String akademska_godina, String sati_predavanja, String sati_LV) {
        sPredmet = predmet;

        oPredmet.setText(predmet);
        oProfesor.setText(ime_profesora);
        oAkGod.setText(akademska_godina);
        oSatiPredavanja.setText(sati_predavanja);
        oSatiLva.setText(sati_LV);
    }

}
