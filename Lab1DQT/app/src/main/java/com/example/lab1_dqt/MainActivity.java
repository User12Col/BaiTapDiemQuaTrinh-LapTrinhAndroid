package com.example.lab1_dqt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listLayout = findViewById(R.id.listLayout);

        ArrayList<String> list = new ArrayList<>();
        list.add("Table");
        list.add("Linear");
        list.add("Relative");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listLayout.setAdapter(adapter);

        listLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent a = new Intent(MainActivity.this, Table.class);
                    startActivity(a);
                } else if(i == 1){
                    Intent a = new Intent(MainActivity.this, Linear.class);
                    startActivity(a);
                } else if(i == 2){
                    Intent a = new Intent(MainActivity.this, Relative.class);
                    startActivity(a);
                }
            }
        });
    }
}