package com.example.lv1_baksaj.fragments;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.lv1_baksaj.ApiManager;
import com.example.lv1_baksaj.Course;
import com.example.lv1_baksaj.CourseApiService;
import com.example.lv1_baksaj.CourseResponse;
import com.example.lv1_baksaj.Instructor;
import com.example.lv1_baksaj.MainActivity;
import com.example.lv1_baksaj.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PageFragment2 extends Fragment implements AdapterView.OnItemSelectedListener, Callback<CourseResponse>{

    private static final String TAG = "MyActivity";
    private CourseResponse courseResponse = new CourseResponse();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Instructor> instruktori = new ArrayList<>();
    private ArrayList<String> predmeti = new ArrayList<>();
    private ArrayList<String> nazivi_instruktora = new ArrayList<>();
    private TextInputLayout oPredmet;
    private TextInputLayout oProfesor;
    private TextInputLayout oAkGod;
    private TextInputLayout oSatiPredavanja;
    private TextInputLayout oSatiLva;
    private Button oDaljeButton;
    private Spinner oSpPredmet;
    private Spinner oSpProfesor;

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
        oSpPredmet = (Spinner) view.findViewById(R.id.spPredmet);
        oSpProfesor = (Spinner) view.findViewById(R.id.spProfesor);
        oAkGod = view.findViewById(R.id.editTextNumber);
        oSatiPredavanja = view.findViewById(R.id.editTextNumber2);
        oSatiLva = view.findViewById(R.id.editTextNumber3);

        //oPredmet.getEditText().addTextChangedListener(personalInfoWatcher);
        //oProfesor.getEditText().addTextChangedListener(personalInfoWatcher);
        oAkGod.getEditText().addTextChangedListener(personalInfoWatcher);
        oSatiPredavanja.getEditText().addTextChangedListener(personalInfoWatcher);
        oSatiLva.getEditText().addTextChangedListener(personalInfoWatcher);

        oDaljeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCurrentItem(2,true);
            }
        });
        ApiManager.getInstanca().service().getCourses().enqueue(this);
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
            //predmet = oPredmet.getEditText().getText().toString();
            //profesor = oProfesor.getEditText().getText().toString();
            akGod = oAkGod.getEditText().getText().toString();
            satiPredavanja = oSatiPredavanja.getEditText().getText().toString();
            satiLva = oSatiLva.getEditText().getText().toString();

            studentInfoListener.onStudentInfoSent(predmet, profesor, akGod, satiPredavanja, satiLva);
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


    @Override
    public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {

        if (response.isSuccessful() && response.body() != null){
            courseResponse = response.body();
            courses = courseResponse.getCourses();

            for (Course course : courses){
                if (course.getTitle() != null && !course.getTitle().matches("")){
                    predmeti.add(course.getTitle());
                }
            }
            ArrayAdapter<String> adapterPredmet = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, predmeti);
            oSpPredmet.setAdapter(adapterPredmet);
            oSpPredmet.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onFailure(Call<CourseResponse> call, Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spPredmet){
            Log.d(TAG, "onItemSelected: " + oSpPredmet.getSelectedItem());

            for (Course course : courses){
                if (oSpPredmet.getSelectedItem() == course.getTitle()){
                    instruktori = course.getInstructor();

                    if (instruktori != null){
                        nazivi_instruktora.clear();

                        for (Instructor instructor : instruktori){
                            nazivi_instruktora.add(instructor.getName());

                            Log.d(TAG, "onResponse: " + instructor.getName());
                        }
                    }
                }
            }
            ArrayAdapter<String> adapterProfesor =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nazivi_instruktora);
            oSpProfesor.setAdapter(adapterProfesor);
            oSpProfesor.setOnItemSelectedListener(this);

            predmet = oSpPredmet.getSelectedItem().toString();
            studentInfoListener.onStudentInfoSent(predmet, profesor, akGod, satiPredavanja, satiLva);
        }
        else if(parent.getId() == R.id.spProfesor)
        {
            profesor =  oSpProfesor.getSelectedItem().toString();
            studentInfoListener.onStudentInfoSent(predmet, profesor, akGod, satiPredavanja,satiLva);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
