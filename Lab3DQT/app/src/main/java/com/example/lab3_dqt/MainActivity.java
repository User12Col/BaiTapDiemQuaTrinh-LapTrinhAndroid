package com.example.lab3_dqt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> list;
    ListView listFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFood = findViewById(R.id.listFood);

        list = new ArrayList<>();
        list.add("Proteins");
        list.add("Grains and Starches");
        list.add("Vitamins");
        list.add("Nutraceuticals");
        list.add("Fats and Oils");
        list.add("Amino Acids");
        list.add("Fibers and Legumes");
        list.add("Minerals");
        list.add("Processing functional ingredients");
        list.add("Preservatives");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listFood.setAdapter(adapter);

        listFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("numFood",i);
                startActivity(intent);
            }
        });
    }
}