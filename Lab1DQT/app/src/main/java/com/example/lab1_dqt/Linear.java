package com.example.lab1_dqt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class Linear extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        SeekBar barColor = findViewById(R.id.barColor);
        LinearLayout topLeft = findViewById(R.id.bgTopLeft);
        LinearLayout botLeft = findViewById(R.id.bgBotLeft);
        LinearLayout topRight = findViewById(R.id.bgTopRight);
        LinearLayout midRight = findViewById(R.id.bgMidRight);
        LinearLayout botRight = findViewById(R.id.bgBotRight);

        topLeft.setBackgroundColor(Color.rgb(98, 3, 252));
        botLeft.setBackgroundColor(Color.rgb(248, 3, 252));
        topRight.setBackgroundColor(Color.rgb(184, 60, 35));
        midRight.setBackgroundColor(Color.rgb(224, 212, 209));
        botRight.setBackgroundColor(Color.rgb(38, 58, 150));

        barColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                topLeft.setBackgroundColor(Color.rgb(98+i, 3+i, 252+i));
                botLeft.setBackgroundColor(Color.rgb(248+i, 3+i, 252+i));
                topRight.setBackgroundColor(Color.rgb(184+i, 60+i, 35+i));
                midRight.setBackgroundColor(Color.rgb(224+i, 212+i, 209+i));
                botRight.setBackgroundColor(Color.rgb(38+i, 58+i, 150+i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.aboutMe){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_layout);

            Button btnMore = dialog.findViewById(R.id.btnMore);
            Button btnExit = dialog.findViewById(R.id.btnExit);

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri url = Uri.parse("https://www.facebook.com/NakGameMaster");
                    Intent intent = new Intent(Intent.ACTION_VIEW,url);
                    startActivity(intent);
                }
            });

            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.hide();
                }
            });

            dialog.show();
        }


        return super.onOptionsItemSelected(item);
    }
}