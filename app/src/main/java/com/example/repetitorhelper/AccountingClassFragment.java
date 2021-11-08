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
 * Use the {@link AccountingClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountingClassFragment extends Fragment {
    Button btnCancelClass;

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
        btnCancelClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CancelClassActivity.class);
                startActivity(intent);
            }
        });
        return root;

    }
}