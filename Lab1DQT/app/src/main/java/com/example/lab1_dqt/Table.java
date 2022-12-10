package com.example.lab1_dqt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Table extends AppCompatActivity {

    TextView bg00, bg01, bg02, bg10, bg11, bg12, bg20, bg21, bg22, bg30, bg31, bg32, bg40, bg41, bg42, bg50, bg51, bg52;
    SeekBar barColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        reference();
        bg00.setBackgroundColor(Color.rgb(98, 3, 252));
        bg10.setBackgroundColor(Color.rgb(98, 3, 252));
        bg20.setBackgroundColor(Color.rgb(98, 3, 252));

        bg30.setBackgroundColor(Color.rgb(248, 3, 252));
        bg40.setBackgroundColor(Color.rgb(248, 3, 252));
        bg50.setBackgroundColor(Color.rgb(248, 3, 252));

        bg01.setBackgroundColor(Color.rgb(184, 60, 35));
        bg02.setBackgroundColor(Color.rgb(184, 60, 35));
        bg11.setBackgroundColor(Color.rgb(184, 60, 35));
        bg12.setBackgroundColor(Color.rgb(184, 60, 35));

        bg21.setBackgroundColor(Color.rgb(224, 212, 209));
        bg22.setBackgroundColor(Color.rgb(224, 212, 209));
        bg31.setBackgroundColor(Color.rgb(224, 212, 209));
        bg32.setBackgroundColor(Color.rgb(224, 212, 209));

        bg41.setBackgroundColor(Color.rgb(38, 58, 150));
        bg42.setBackgroundColor(Color.rgb(38, 58, 150));
        bg51.setBackgroundColor(Color.rgb(38, 58, 150));
        bg52.setBackgroundColor(Color.rgb(38, 58, 150));

        barColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                bg00.setBackgroundColor(Color.rgb(98+i, 3+i, 252+i));
                bg10.setBackgroundColor(Color.rgb(98+i, 3+i, 252+i));
                bg20.setBackgroundColor(Color.rgb(98+i, 3+i, 252+i));

                bg30.setBackgroundColor(Color.rgb(248+i, 3+i, 252+i));
                bg40.setBackgroundColor(Color.rgb(248+i, 3+i, 252+i));
                bg50.setBackgroundColor(Color.rgb(248+i, 3+i, 252+i));

                bg01.setBackgroundColor(Color.rgb(184+i, 60+i, 35+i));
                bg02.setBackgroundColor(Color.rgb(184+i, 60+i, 35+i));
                bg11.setBackgroundColor(Color.rgb(184+i, 60+i, 35+i));
                bg12.setBackgroundColor(Color.rgb(184+i, 60+i, 35+i));

                bg21.setBackgroundColor(Color.rgb(224+i, 212+i, 209+i));
                bg22.setBackgroundColor(Color.rgb(224+i, 212+i, 209+i));
                bg31.setBackgroundColor(Color.rgb(224+i, 212+i, 209+i));
                bg32.setBackgroundColor(Color.rgb(224+i, 212+i, 209+i));

                bg41.setBackgroundColor(Color.rgb(38+i, 58+i, 150+i));
                bg42.setBackgroundColor(Color.rgb(38+i, 58+i, 150+i));
                bg51.setBackgroundColor(Color.rgb(38+i, 58+i, 150+i));
                bg52.setBackgroundColor(Color.rgb(38+i, 58+i, 150+i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void reference(){
        bg00 = findViewById(R.id.bg00);
        bg01 = findViewById(R.id.bg01);
        bg02 = findViewById(R.id.bg02);
        bg10 = findViewById(R.id.bg10);
        bg11 = findViewById(R.id.bg11);
        bg12 = findViewById(R.id.bg12);
        bg20 = findViewById(R.id.bg20);
        bg21 = findViewById(R.id.bg21);
        bg22 = findViewById(R.id.bg22);
        bg30 = findViewById(R.id.bg30);
        bg31 = findViewById(R.id.bg31);
        bg32 = findViewById(R.id.bg32);
        bg40 = findViewById(R.id.bg40);
        bg41 = findViewById(R.id.bg41);
        bg42 = findViewById(R.id.bg42);
        bg50 = findViewById(R.id.bg50);
        bg51 = findViewById(R.id.bg51);
        bg52 = findViewById(R.id.bg52);
        barColor = findViewById(R.id.barColorTable);

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