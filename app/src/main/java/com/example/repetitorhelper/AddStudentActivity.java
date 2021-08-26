package com.example.repetitorhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddStudentActivity extends AppCompatActivity {
    Button btnSaveStudent;
    Spinner spPlaceOfClass;
    String [] placeOfClass = {"Онлайн", "Очно"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        btnSaveStudent = findViewById(R.id.btn_save_student);
        spPlaceOfClass = findViewById(R.id.sp_place_of_class);
        setAdapterForSpinner(spPlaceOfClass);
        btnSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setAdapterForSpinner (Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, placeOfClass);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}