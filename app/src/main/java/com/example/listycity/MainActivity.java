package com.example.listycity;

import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;

    private Button AddCity, DeleteCity, ConfirmCity;
    private EditText editCity;

    private int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        AddCity = findViewById(R.id.button_add_top);
        DeleteCity = findViewById(R.id.button_delete);
        ConfirmCity = findViewById(R.id.button_confirm);
        editCity = findViewById(R.id.edit_city_name);

        String []cities ={"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>( this, R.layout.content, R.id.context_view, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        AddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCityFromInput();
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                cityList.setItemChecked(position, true);
                DeleteCity.setEnabled(true);

           }
        });

        ConfirmCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCityFromInput();
            }
        });

        DeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected == -1) {
                    Toast.makeText(MainActivity.this, "Choose a City To Remove", Toast.LENGTH_SHORT).show();
                    return;
                }
                dataList.remove(selected);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
                selected = -1;
                DeleteCity.setEnabled(false);

            }
        });

    }

    private void addCityFromInput() {
        String name = editCity.getText().toString().trim();
        if (!name.isEmpty()) {
            dataList.add(name);
            cityAdapter.notifyDataSetChanged();
            editCity.setText("");  // clear input

            selected = -1;
            DeleteCity.setEnabled(false);
            cityList.clearChoices();

        }
    }

}



