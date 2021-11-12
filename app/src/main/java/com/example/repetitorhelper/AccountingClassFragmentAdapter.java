package com.example.repetitorhelper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class AccountingClassFragmentAdapter extends RecyclerView.Adapter<AccountingClassFragmentAdapter.MyViewHolder> {
    ArrayList<String> name;
    ArrayList<String> cancelCountClass;
    ArrayList<String> money;
    ArrayList<String> priceOfOneClass;
    ArrayList<String> surname;
    Context context;
    SQLBaseStudents sqlBaseStudents;
    String surnameCancelClass;
    int cancelCount, moneyWithCancelCount;
    public AccountingClassFragmentAdapter(Context ct, ArrayList<String> n, ArrayList<String> c, ArrayList<String> m, ArrayList<String> sn, ArrayList<String> pOOc){
        this.name = n;
        this.context = ct;
        this.cancelCountClass = c;
        this.money = m;
        this.surname = sn;
        this.priceOfOneClass = pOOc;
    }
    @NonNull
    @Override
    public AccountingClassFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.raw_accounting_class, parent, false);
        sqlBaseStudents = new SQLBaseStudents(context);
        return new AccountingClassFragmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountingClassFragmentAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(name.get(position));
        holder.tvSurname.setText(surname.get(position));
        holder.tvMoney.setText(money.get(position));
        holder.tvCount.setText(cancelCountClass.get(position));
        holder.btnSendMoney.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Отмена занятия")
                        .setMessage("Вы уверены что ваш ученик отменил занятие?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                surnameCancelClass = (String) holder.tvSurname.getText();
                                moneyWithCancelCount = Integer.parseInt(money.get(position)) + Integer.parseInt(priceOfOneClass.get(position));
                                cancelCount = Integer.parseInt(cancelCountClass.get(position)) + 1;
                                sqlBaseStudents.upgradeCountCancelClass(surnameCancelClass,cancelCount);
                                holder.tvCount.setText(String.valueOf(cancelCount));
                                holder.tvMoney.setText(String.valueOf(moneyWithCancelCount));
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
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvSurname;
        TextView tvCount;
        TextView tvMoney;
        Button btnSendMoney;
        CardView cvTimeTable;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_accounting_class);
            tvSurname = itemView.findViewById(R.id.tv_surname_accounting_class);
            tvCount = itemView.findViewById(R.id.tv_cancel_count_accounting_class);
            tvMoney = itemView.findViewById(R.id.tv_money_accounting_class);
            cvTimeTable = itemView.findViewById(R.id.card_view_accounting_class);
            btnSendMoney = itemView.findViewById(R.id.btn_add_cancel_accounting_class);
        }
    }
}
