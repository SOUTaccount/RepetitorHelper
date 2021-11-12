package com.example.repetitorhelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountingClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountingClassFragment extends Fragment {
    Button btnCancelClass;
    SQLBaseStudents sqlBaseStudents;
    RecyclerView rvCancel;
    ArrayList<String> alName, alSurname, alPrice, alCount, alPriceOfOneClass;
    Boolean flag = false;

    public AccountingClassFragment() {
    }

    public static AccountingClassFragment newInstance() {
        return new AccountingClassFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_accounting_class, container, false);
        btnCancelClass = root.findViewById(R.id.btn_cancel_class);
        rvCancel = root.findViewById(R.id.rw_cancel_class);
        sqlBaseStudents = new SQLBaseStudents(getContext());
        if (sqlBaseStudents.checkTable()!= 0){
            setAdapter();
        }
        btnCancelClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                Intent intent = new Intent(getContext(), CancelClassActivity.class);
                startActivity(intent);
            }
        });
        return root;

    }
    private void setAdapter(){
        alCount = sqlBaseStudents.getCountCancelClass();
        alName = sqlBaseStudents.getNameCancelClass();
        alSurname = sqlBaseStudents.getSurnameCancelClass();
        alPrice = sqlBaseStudents.getMoneyCancelClass();
        alPriceOfOneClass = sqlBaseStudents.getMoneyCancelClass();
        for (int i = 0; i < alCount.size(); i++) {
            int multiply = Integer.parseInt(alCount.get(i)) * Integer.parseInt(alPrice.get(i));
            alPrice.set(i,String.valueOf(multiply));
        }
        Log.d("SQLLog","Count = " + sqlBaseStudents.getAllCancelClass().get(0));
        AccountingClassFragmentAdapter adapter = new AccountingClassFragmentAdapter(getContext(),alName,alCount,alPrice,alSurname,alPriceOfOneClass);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvCancel.setLayoutManager(linearLayoutManager);
        rvCancel.setAdapter(adapter);
    }
}