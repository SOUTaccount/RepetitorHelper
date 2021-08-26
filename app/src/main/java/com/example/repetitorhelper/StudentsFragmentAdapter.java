package com.example.repetitorhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentsFragmentAdapter extends RecyclerView.Adapter<StudentsFragmentAdapter.MyViewHolder> {

    ArrayList<String> students;
    Context context;

    public StudentsFragmentAdapter(Context ct, ArrayList<String> als){
        this.context = ct;
        this.students = als;
    }
    @NonNull
    @Override
    public StudentsFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.raw_students, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsFragmentAdapter.MyViewHolder holder, int position) {
        holder.tvStudentName.setText(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tv_student_name);
        }
    }
}
