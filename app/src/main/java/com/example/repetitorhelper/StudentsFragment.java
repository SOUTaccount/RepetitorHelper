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
 * Use the {@link StudentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentsFragment extends Fragment {
    Button btnAddStudent;
    RecyclerView rvStudents;
    SQLBaseStudents sqlBaseStudents;
    ArrayList<String> students;
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
        setAdapterWithNames();
        super.onResume();
    }
    public void setAdapterWithNames (){
        students = sqlBaseStudents.getAllNames();
        studentsFragmentAdapter = new StudentsFragmentAdapter(getContext(),students);
        rvStudents.setAdapter(studentsFragmentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvStudents.setLayoutManager(linearLayoutManager);
    }
}