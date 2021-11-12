package com.example.repetitorhelper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.MyViewHolder> {
    ArrayList<String> name;
    ArrayList<String> countClass;
    ArrayList<String> money;
    ArrayList<String> surname;
    ArrayList<String> countWithoutCancel;
    ArrayList<Integer> colors;
    SQLBaseStudents sqlBaseStudents;
    Context context;
    public TimeTableAdapter(Context ct, ArrayList<String> n, ArrayList<String> c, ArrayList<String> m, ArrayList<String> sn, ArrayList<String> cwc,
                            ArrayList<Integer> clrs){
        this.name = n;
        this.context = ct;
        this.countClass = c;
        this.money = m;
        this.surname = sn;
        this.countWithoutCancel = cwc;
        this.colors = clrs;
    }
    @NonNull
    @Override
    public TimeTableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.raw_time_table, parent, false);
        sqlBaseStudents = new SQLBaseStudents(context);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TimeTableAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(name.get(position));
        holder.tvSurname.setText(surname.get(position));
        holder.tvMoney.setText(money.get(position));
        holder.tvCount.setText(countClass.get(position) + "/" + countWithoutCancel.get(position));
        holder.cvTimeTable.setBackgroundColor(colors.get(position));
        holder.btnSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Оплата занятий")
                        .setMessage("Вы уверены что ваш ученик оплатил занятия?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                holder.cvTimeTable.setBackgroundColor(Color.GREEN);
                                sqlBaseStudents.upgradeColor(String.valueOf(holder.tvSurname.getText()),Color.GREEN);
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

    @Override
    public int getItemCount() {
        return name.size();
    }
public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvSurname;
        TextView tvCount;
        TextView tvMoney;
        Button btnSendMoney;
        CardView cvTimeTable;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name_time_table);
        tvSurname = itemView.findViewById(R.id.tv_surname_time_table);
        tvCount = itemView.findViewById(R.id.tv_count_time_table);
        tvMoney = itemView.findViewById(R.id.tv_money_time_table);
        cvTimeTable = itemView.findViewById(R.id.card_view_time_table);
        btnSendMoney = itemView.findViewById(R.id.btn_send_money_time_table);
    }
}
}
