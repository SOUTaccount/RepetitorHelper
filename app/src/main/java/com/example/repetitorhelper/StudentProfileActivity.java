package com.example.repetitorhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentProfileActivity extends AppCompatActivity {
    SQLBaseStudents sqlBaseStudents;
    String surname;
    String name;
    String phoneNum;
    ArrayList<String> students;
    TextView tvNameStudent;
    TextView tvSurnameStudent;
    TextView tvPhoneStudent;
    TextView tvCountClassStudent;
    TextView tvIncome;
    TextView tvPriceClassStudent;
    TextView tvTypeClass;
    String LOG_TAG = "SqlLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        tvNameStudent = findViewById(R.id.tv_student_name_profile);
        tvSurnameStudent = findViewById(R.id.tv_student_surname_profile);
        tvPhoneStudent = findViewById(R.id.tv_phone_student_profile);
        tvCountClassStudent = findViewById(R.id.tv_count_class_profile);
        tvIncome = findViewById(R.id.tv_income_profile);
        tvPriceClassStudent = findViewById(R.id.tv_price_class_profile);
        tvTypeClass = findViewById(R.id.tv_type_of_class_profile);
        Intent intent = getIntent();
        surname = intent.getStringExtra("surnames");
        name = intent.getStringExtra("names");
        setAllData();


    }
    public void setAllData(){
        tvSurnameStudent.setText(surname);
        tvNameStudent.setText(name);
        sqlBaseStudents = new SQLBaseStudents(this);
        phoneNum = sqlBaseStudents.getPhoneNum(surname);
        tvPhoneStudent.setText(phoneNum);
        String countClass,priceClass,typeClass,incomeStr;
        int income,count,price;
        countClass = sqlBaseStudents.getCountClass(surname);
        priceClass = sqlBaseStudents.getPriceClass(surname);
        typeClass = sqlBaseStudents.getTypeClass(surname);
        count = Integer.parseInt(countClass);
        price = Integer.parseInt(priceClass);
        income = count * price;
        incomeStr = String.valueOf(income);
        tvCountClassStudent.setText(countClass);
        tvPriceClassStudent.setText(priceClass);
        tvTypeClass.setText(typeClass);

        tvIncome.setText(incomeStr);
        Log.d(LOG_TAG,"income = " + incomeStr);
    }
}