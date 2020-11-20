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

public class PageFragment1 extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private String mParam1;
    private String mParam2;

    private EditText oIme, oPrezime, oDatum;
    private String ime, prezime, datum;
    private Button oButton;
    private PersonalInfoListener personalInfoListener;

    public interface PersonalInfoListener {
        void onPersonalInfoSent(String ime, String prezime, String datum);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oIme = view.findViewById(R.id.textInputLayout);
        oPrezime = view.findViewById(R.id.textInputLayout2);
        oDatum = view.findViewById(R.id.editTextDate);
        oButton = view.findViewById(R.id.button);

        oIme.addTextChangedListener(personalInfoWatcher);
        oPrezime.addTextChangedListener(personalInfoWatcher);
        oDatum.addTextChangedListener(personalInfoWatcher);

        oButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCurrentItem(1,true);
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
            ime = oIme.getText().toString();
            prezime = oPrezime.getText().toString();
            datum = oDatum.getText().toString();

            personalInfoListener.onPersonalInfoSent(ime,prezime,datum);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  PersonalInfoListener) {
            personalInfoListener = (PersonalInfoListener) context;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        personalInfoListener = null;
    }
}
