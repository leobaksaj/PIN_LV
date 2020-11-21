package com.example.lv1_baksaj.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lv1_baksaj.MainActivity;
import com.example.lv1_baksaj.R;
import com.google.android.material.textfield.TextInputLayout;

public class PageFragment1 extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private String mParam1;
    private String mParam2;

    private ImageView imageView;
    private TextInputLayout oIme;
    private TextInputLayout oPrezime;
    private EditText oDatum;
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
        imageView = view.findViewById(R.id.imageView2);
        oIme = (TextInputLayout) view.findViewById(R.id.textInputLayout);
        oPrezime = (TextInputLayout) view.findViewById(R.id.textInputLayout2);
        oDatum = view.findViewById(R.id.editTextDate);
        oButton = view.findViewById(R.id.button);

        oIme.getEditText().addTextChangedListener(personalInfoWatcher);
        oPrezime.getEditText().addTextChangedListener(personalInfoWatcher);
        oDatum.addTextChangedListener(personalInfoWatcher);

        oButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCurrentItem(1,true);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
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
            ime = oIme.getEditText().getText().toString();
            prezime = oPrezime.getEditText().getText().toString();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
