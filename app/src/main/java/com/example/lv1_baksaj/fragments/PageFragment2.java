package com.example.lv1_baksaj.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lv1_baksaj.MainActivity;
import com.example.lv1_baksaj.R;

public class PageFragment2 extends Fragment {

    private EditText oPredmet;
    private EditText oProfesor;
    private EditText oAkGod;
    private EditText oSatiPredavanja;
    private EditText oSatiLva;
    private Button oDaljeButton;

    private String predmet;
    private String profesor;
    private String akGod;
    private String satiPredavanja;
    private String satiLva;
    private StudentInfoListener studentInfoListener;

    public interface StudentInfoListener {
        void onStudentInfoSent(String predmet, String ime_profesora, String akademska_godina, String sati_predavanja, String sati_LV);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.student_info_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oDaljeButton = view.findViewById(R.id.button2);
        oPredmet = view.findViewById(R.id.textInputLayout3);
        oProfesor = view.findViewById(R.id.textInputLayout2);
        oAkGod = view.findViewById(R.id.editTextNumber);
        oSatiPredavanja = view.findViewById(R.id.editTextNumber2);
        oSatiLva = view.findViewById(R.id.editTextNumber3);

        oPredmet.addTextChangedListener(personalInfoWatcher);
        oProfesor.addTextChangedListener(personalInfoWatcher);
        oAkGod.addTextChangedListener(personalInfoWatcher);
        oSatiPredavanja.addTextChangedListener(personalInfoWatcher);
        oSatiLva.addTextChangedListener(personalInfoWatcher);

        oDaljeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCurrentItem(2,true);
            }
        });

    }

    private TextWatcher personalInfoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            predmet = oPredmet.getEditableText().toString();
            profesor = oProfesor.getEditableText().toString();
            akGod = oAkGod.getEditableText().toString();
            satiPredavanja = oSatiPredavanja.getEditableText().toString();
            satiLva = oSatiLva.getEditableText().toString();

            studentInfoListener.onStudentInfoSent(predmet, profesor, akGod,satiPredavanja,satiLva);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  StudentInfoListener) {
            studentInfoListener = (StudentInfoListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        studentInfoListener = null;
    }
}
