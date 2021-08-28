package com.example.repetitorhelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentsFragmentAdapter extends RecyclerView.Adapter<StudentsFragmentAdapter.MyViewHolder> {

    ArrayList<String> studentsNames;
    ArrayList<String> studentsSurnames;
    Context context;
    String EXTRA_SURNAME = "surnames";
    String EXTRA_NAME = "names";

    public StudentsFragmentAdapter(Context ct, ArrayList<String> als, ArrayList<String> alss){
        this.context = ct;
        this.studentsNames = als;
        this.studentsSurnames = alss;
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
        holder.tvStudentName.setText(studentsNames.get(position));
        holder.tvStudentNumber.setText(String.valueOf(position + 1));
        holder.tvStudentSurname.setText(studentsSurnames.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StudentProfileActivity.class);
                intent.putExtra(EXTRA_SURNAME,holder.tvStudentSurname.getText());
                intent.putExtra(EXTRA_NAME,holder.tvStudentName.getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        CardView cardView;
        TextView tvStudentNumber;
        TextView tvStudentSurname;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tv_student_name);
            tvStudentSurname = itemView.findViewById(R.id.tv_student_surname);
            cardView = itemView.findViewById(R.id.card_view_student);
            tvStudentNumber = itemView.findViewById(R.id.tv_number_student);
        }
    }
}
