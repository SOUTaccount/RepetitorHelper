package com.example.repetitorhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
    EditText edtCountClassStudent;
    TextView tvIncome;
    EditText edtPriceClassStudent;
    TextView tvTypeClass;
    String LOG_TAG = "SqlLog";
    FloatingActionButton fabAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        tvNameStudent = findViewById(R.id.tv_student_name_profile);
        tvSurnameStudent = findViewById(R.id.tv_student_surname_profile);
        tvPhoneStudent = findViewById(R.id.tv_phone_student_profile);
        edtCountClassStudent = findViewById(R.id.tv_count_class_profile);
        tvIncome = findViewById(R.id.tv_income_profile);
        edtPriceClassStudent = findViewById(R.id.tv_price_class_profile);
        tvTypeClass = findViewById(R.id.tv_type_of_class_profile);
        fabAccept = findViewById(R.id.floatingActionButton);
        Intent intent = getIntent();
        surname = intent.getStringExtra("surnames"); // Получаем переданный в интент стринг
        name = intent.getStringExtra("names"); // Получаем переданный в интент стринг
        setAllData();
        fabAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlBaseStudents.upgradeCountClass(surname,edtCountClassStudent.getText().toString());
                sqlBaseStudents.upgradePrice(surname,edtPriceClassStudent.getText().toString());
                Toast.makeText(v.getContext(),"Данные обновлены",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent1);
            }
        });

        /**
         Присваиваем значения, которые берем из бд
         */
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
        count = Integer.parseInt(countClass); //делаем интом количество занятий
        price = Integer.parseInt(priceClass);//делаем интом цену занятия
        income = count * price; // считаем прибыль за 1 месяц за 1 ученика
        incomeStr = String.valueOf(income);
        edtCountClassStudent.setText(countClass);
        edtPriceClassStudent.setText(priceClass);
        tvTypeClass.setText(typeClass);

        tvIncome.setText(incomeStr);
        Log.d(LOG_TAG,"income = " + incomeStr);
    }
}