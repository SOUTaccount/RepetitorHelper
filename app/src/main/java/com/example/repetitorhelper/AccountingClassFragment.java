package com.example.repetitorhelper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountingClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountingClassFragment extends Fragment {

    public AccountingClassFragment() {
    }

    public static AccountingClassFragment newInstance() {
        return new AccountingClassFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accounting_class, container, false);
    }
}