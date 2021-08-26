package com.example.repetitorhelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentsFragment extends Fragment {
    Button btnAddStudent;
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
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddStudentActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

}