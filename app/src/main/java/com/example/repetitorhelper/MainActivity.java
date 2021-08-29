package com.example.repetitorhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //Поиск меню навигации, привязка к нему прослушивателя кликов, выбор начального фрагмента.
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(TimeTableFragment.newInstance());
    }
    //Реализация прослушивателя, задаем значения выбранных фрагментов
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_time_table:
                    loadFragment(TimeTableFragment.newInstance()); // используется метод newInstance, определенный в самом фрагменте, создает фрагмент
                    return true;
                case R.id.navigation_students:
                    loadFragment(StudentsFragment.newInstance());
                    return true;
                case R.id.navigation_accounting_class:
                    loadFragment(AccountingClassFragment.newInstance());
                    return true;
            }
            return false;
        }
    };
    //Метод, позволяющий заменять фрагмент, который открыт в данный момент
    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }
}