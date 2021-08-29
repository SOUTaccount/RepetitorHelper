package com.example.repetitorhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddStudentActivity extends AppCompatActivity {
    Button btnSaveStudent;
    Spinner spPlaceOfClass;
    String [] placeOfClass = {"Онлайн", "Очно"};
    String name,surname,count,price,type,phone;
    EditText edtName;
    EditText edtSurname;
    EditText edtPrice;
    EditText edtCount;
    EditText edtPhone;
    SQLBaseStudents sqlBaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        btnSaveStudent = findViewById(R.id.btn_save_student);
        spPlaceOfClass = findViewById(R.id.sp_place_of_class);
        edtName = findViewById(R.id.edt_name_student);
        edtSurname = findViewById(R.id.edt_surname_student);
        edtPrice = findViewById(R.id.edt_price_class);
        edtCount = findViewById(R.id.edt_count_of_class_student);
        edtPhone = findViewById(R.id.edt_phone_student);
        setAdapterForSpinner(spPlaceOfClass);
        btnSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataInSql();
                finish(); //завершаю работу активности
            }
        });
    }
    /**
     Выделение памяти под адаптер для спиннера и его привязка.
     Добавление прослушивателей
     */
    public void setAdapterForSpinner (Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, placeOfClass);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = String.valueOf(parent.getItemAtPosition(position)); // записываю данные в стринг, чтобы передать в бд
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
    /**
     Добавляем все данные из заполненных полей в бд
     */
    public void addDataInSql (){
        sqlBaseStudents = new SQLBaseStudents(this);
        name = edtName.getText().toString();
        surname = edtSurname.getText().toString();
        count = edtCount.getText().toString();
        price = edtPrice.getText().toString();
        phone = edtPhone.getText().toString();
        sqlBaseStudents.insertData(name,surname,type,price,count,phone);
    }
}