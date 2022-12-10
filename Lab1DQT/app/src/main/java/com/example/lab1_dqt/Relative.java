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

public class Relative extends AppCompatActivity {

    TextView reTopLeft, reBotLeft, reMidRight, reBotRight, reTopRight;
    SeekBar barColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
        reference();

        reTopLeft.setBackgroundColor(Color.rgb(98, 3, 252));
        reBotLeft.setBackgroundColor(Color.rgb(248, 3, 252));
        reTopRight.setBackgroundColor(Color.rgb(184, 60, 35));
        reMidRight.setBackgroundColor(Color.rgb(224, 212, 209));
        reBotRight.setBackgroundColor(Color.rgb(38, 58, 150));

        barColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                reTopLeft.setBackgroundColor(Color.rgb(98+i, 3+i, 252+i));
                reBotLeft.setBackgroundColor(Color.rgb(248+i, 3+i, 252+i));
                reTopRight.setBackgroundColor(Color.rgb(184+i, 60+i, 35+i));
                reMidRight.setBackgroundColor(Color.rgb(224+i, 212+i, 209+i));
                reBotRight.setBackgroundColor(Color.rgb(38+i, 58+i, 150+i));
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
        reTopLeft = findViewById(R.id.reTopLeft);
        reBotLeft = findViewById(R.id.reBotLeft);

        reMidRight = findViewById(R.id.reMidRight);
        reBotRight = findViewById(R.id.reBotRight);
        reTopRight = findViewById(R.id.reTopRight);

        barColor = findViewById(R.id.reBarColor);
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