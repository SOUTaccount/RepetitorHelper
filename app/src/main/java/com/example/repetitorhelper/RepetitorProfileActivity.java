package com.example.repetitorhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class RepetitorProfileActivity extends AppCompatActivity {
    TextView tvCountClass, tvCountCancelClass, tvCountStudent, tvPriceOfMonth, tvPriceOfMonthWithCancel;
    SQLBaseStudents sqlBaseStudents;
    ArrayList<String> alCount, alCancelCount, alPrice, alPriceCancel;
    int countClass, countCancelClass, countStudent, price, priceWithCancel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repetitor_profile);
        tvCountClass = findViewById(R.id.tv_count_class_repetitor_profile);
        tvCountCancelClass = findViewById(R.id.tv_count_cancel_class_repetitor_profile);
        tvCountStudent = findViewById(R.id.tv_count_students_repetitor_profile);
        tvPriceOfMonth = findViewById(R.id.tv_all_price_of_month);
        tvPriceOfMonthWithCancel = findViewById(R.id.tv_price_with_cancel_class);
        sqlBaseStudents = new SQLBaseStudents(this);
        if (sqlBaseStudents.checkTable() != 0){
        alCount = sqlBaseStudents.getAllClassCounts();
        alCancelCount = sqlBaseStudents.getCountCancelClass();
        alPrice = sqlBaseStudents.getAllPriceOfClass();
        alPriceCancel = sqlBaseStudents.getMoneyCancelClass();
        countStudent = alCount.size();
        for (int i = 0; i < alCount.size(); i++) {
            countClass += Integer.parseInt(alCount.get(i));
            price += Integer.parseInt(alCount.get(i)) * Integer.parseInt(alPrice.get(i));
        }
        for (int i = 0; i <alCancelCount.size(); i++) {
            countCancelClass += Integer.parseInt(alCancelCount.get(i));
            priceWithCancel += Integer.parseInt(alCancelCount.get(i)) * Integer.parseInt(alPriceCancel.get(i));
        }
        priceWithCancel = price - priceWithCancel;
        }
        tvCountClass.setText(String.valueOf(countClass));
        tvCountCancelClass.setText(String.valueOf(countCancelClass));
        tvCountStudent.setText(String.valueOf(countStudent));
        tvPriceOfMonth.setText(String.valueOf(price));
        tvPriceOfMonthWithCancel.setText(String.valueOf(priceWithCancel));
    }
}