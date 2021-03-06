package com.example.repetitorhelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
 * Use the {@link StudentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentsFragment extends Fragment {
    Button btnAddStudent;
    RecyclerView rvStudents;
    SQLBaseStudents sqlBaseStudents;
    ArrayList<String> studentsNames;
    ArrayList<String> studentsSurnames;
    StudentsFragmentAdapter studentsFragmentAdapter;
    public StudentsFragment() {
    }

    public static StudentsFragment newInstance() {
        return new StudentsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_students, container, false);
        btnAddStudent = root.findViewById(R.id.btn_add_student);
        rvStudents = root.findViewById(R.id.rv_stundets);
        sqlBaseStudents = new SQLBaseStudents(getContext());
        /**
         Проверяем бд на наличие в ней данных, если они не null, тогда
         привязываем адаптер и передаем в него данные из бд
         Присваиваем слушателя, при нажатии на который открывается активность добавления ученика
         */
        if (sqlBaseStudents.checkTable() != 0){
            setAdapterWithNames();
        }
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddStudentActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        setAdapterWithNames(); // опять присваиваем, для того, чтобы обновить данные
        super.onResume();
    }
    /**
     Получаем данные из бд, передаем их адаптеру
     */
    public void setAdapterWithNames (){
        studentsNames = sqlBaseStudents.getAllNames();
        studentsSurnames = sqlBaseStudents.getAllSurnames();
        studentsFragmentAdapter = new StudentsFragmentAdapter(getContext(),studentsNames,studentsSurnames);
        rvStudents.setAdapter(studentsFragmentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvStudents.setLayoutManager(linearLayoutManager);
    }
}