package com.example.repetitorhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class CancelClassActivity extends AppCompatActivity {
    Spinner spCancelClass;
    SQLBaseStudents sqlBaseStudents;
    String surname;
    ArrayList<String> surnamesStudents;
    Button btnAddCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_class);
        spCancelClass = findViewById(R.id.sp_cancel_class);
        sqlBaseStudents = new SQLBaseStudents(this);
        surnamesStudents = new ArrayList<>();
        if (sqlBaseStudents.checkTable()!= 0){
            surnamesStudents = sqlBaseStudents.getSurnameWithoutCancelClass();
            setAdapterForSpinner(spCancelClass);
        }
        btnAddCancel = findViewById(R.id.btn_add_cancel);
        btnAddCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlBaseStudents.upgradeCountCancelClass(surname,1);
                Log.d("SURNAME", "Surname = " + surname);
                Intent intent = new Intent(CancelClassActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setAdapterForSpinner (Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, surnamesStudents);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                surname = String.valueOf(parent.getItemAtPosition(position)); // записываю данные в стринг, чтобы передать в бд
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }
}