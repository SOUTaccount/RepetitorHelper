package com.example.repetitorhelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimeTableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeTableFragment extends Fragment {
    RecyclerView rvTimeTable;
    SQLBaseStudents sqlBaseStudents;
    ArrayList<String> alNames, alSurnames, alCount, alMoney, alSum, alCountWithoutCancel, alSurnamesWithCancelClass;
    ArrayList<Integer> alCountCancel, alColors;
    Button btnProfile;
    FloatingActionButton btnRefresh;
    TextView repetitorProfile;
    int count, price, sum, countCancel;

    public TimeTableFragment() {
    }

    public static TimeTableFragment newInstance() {
        return new TimeTableFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_time_table, container, false);
        rvTimeTable = root.findViewById(R.id.rv_time_table);
        btnRefresh = root.findViewById(R.id.btn_refresh_month);
        repetitorProfile = root.findViewById(R.id.tv_time_table_title);
        sqlBaseStudents = new SQLBaseStudents(getContext());
        if (sqlBaseStudents.checkTable() != 0){
        alNames = sqlBaseStudents.getAllNames();
        alSurnames = sqlBaseStudents.getAllSurnames();
        alCount = sqlBaseStudents.getAllClassCounts();
        alMoney = sqlBaseStudents.getAllPriceOfClass();
        alCountCancel = sqlBaseStudents.getAllCancelClass();
        alCountWithoutCancel = sqlBaseStudents.getAllClassCounts();
        alColors = sqlBaseStudents.getColor();
        alSurnamesWithCancelClass = sqlBaseStudents.getSurnameCancelClass();
            alSum = new ArrayList<>();
        for (int i = 0; i < alMoney.size();i++){
            count = Integer.parseInt(alCount.get(i));
            price = Integer.parseInt(alMoney.get(i));
            countCancel = alCountCancel.get(i);
            sum = (count*price) - (countCancel*price);
            alCount.set(i,String.valueOf(count-countCancel));
            alSum.add(String.valueOf(sum));
        }
        TimeTableAdapter timeTableAdapter = new TimeTableAdapter(getContext(),alNames,alCount,alSum,alSurnames,alCountWithoutCancel,alColors);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTimeTable.setAdapter(timeTableAdapter);
        rvTimeTable.setLayoutManager(linearLayoutManager);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Начало нового месяца")
                        .setMessage("Вы уверены, что хотите обнулить пропуски и оплаты?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                for (int i = 0; i < alSurnamesWithCancelClass.size(); i++) {
                                    sqlBaseStudents.upgradeCountCancelClass(alSurnamesWithCancelClass.get(i),0);
                                }
                                for (int i = 0; i < alSurnames.size(); i++) {
                                    sqlBaseStudents.upgradeColor(alSurnames.get(i),Color.WHITE);
                                }
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fl_content,TimeTableFragment.newInstance());
                                fragmentTransaction.commit();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
        });
        }
        repetitorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RepetitorProfileActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}