package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button btnAddCity, btnDeleteCity, btnConfirm;
    EditText etCity;

    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList =findViewById(R.id.city_list);
        btnAddCity = findViewById(R.id.btn_add_city);
        btnDeleteCity = findViewById(R.id.btn_delete_city);
        btnConfirm = findViewById(R.id.btn_confirm);
        etCity = findViewById(R.id.et_city);
        String []cities ={"Edmonton", "vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            cityList.setItemChecked(position, true); // highlights selection
        });

        // "ADD CITY"
        btnAddCity.setOnClickListener(v -> {
            etCity.requestFocus();
            etCity.setText("");
        });

        // CONFIRM adds the city to display
        btnConfirm.setOnClickListener(v -> {
            String newCity = etCity.getText().toString().trim();
            if (newCity.isEmpty()) {
                Toast.makeText(this, "Type a city name first.", Toast.LENGTH_SHORT).show();
                return;
            }

            dataList.add(0, newCity);
            cityAdapter.notifyDataSetChanged();

            etCity.setText("");
            selectedIndex = -1;
            cityList.clearChoices();
        });

        // DELETE removes city
        btnDeleteCity.setOnClickListener(v -> {
            if (selectedIndex == -1) {
                Toast.makeText(this, "Tap a city to select it first.", Toast.LENGTH_SHORT).show();
                return;
            }

            dataList.remove(selectedIndex);
            cityAdapter.notifyDataSetChanged();

            selectedIndex = -1;
            cityList.clearChoices();
        });
    }
}