package com.example.lab4dqt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OfficalActivity extends AppCompatActivity {

    TextView txtTitleCity, txtOfficialRole, txtOfficialName, txtOfficialParty, txtOfficialAddress, txtOfficialPhone, txtOfficialEmai, txtOfficialWeb;
    ImageView officialImg, youtubeImg, googleImg, twitterImg, facebookImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offical);
        reference();

        Intent intent = getIntent();
        String titleCity = intent.getStringExtra("city");
        txtTitleCity.setText(titleCity);

        Delegate official = (Delegate) intent.getSerializableExtra("officalObject");
        setContent(official);

        facebookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "";
                ArrayList<SocialMedia> socialMedia = official.getSocialMediaList();
                for(int i = 0;i<socialMedia.size();i++){
                    SocialMedia media = socialMedia.get(i);
                    if(media.getType().equals("Facebook")){
                        id = media.getId();
                    }
                }
                String FACEBOOK_URL = "https://www.facebook.com/"+id;
                String urlToUse;
                PackageManager packageManager = getPackageManager();

                try{
                    int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850) {
                        urlToUse = "fb://facewebmodal/f?href=" + FACEBOOK_URL;
                    } else {
                        urlToUse = "fb://page/" + id;
                    }

                } catch (PackageManager.NameNotFoundException e){
                    urlToUse = FACEBOOK_URL;
                }

                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                facebookIntent.setData(Uri.parse(urlToUse));
                startActivity(facebookIntent);

            }
        });

        twitterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "";
                ArrayList<SocialMedia> socialMedia = official.getSocialMediaList();
                for(int i = 0;i<socialMedia.size();i++){
                    SocialMedia media = socialMedia.get(i);
                    if(media.getType().equals("Twitter")){
                        id = media.getId();
                    }
                }

                Intent intent = null;
                try {
                    getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + id));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + id));
                }
                startActivity(intent);
            }
        });

        youtubeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "";
                ArrayList<SocialMedia> socialMedia = official.getSocialMediaList();
                for(int i = 0;i<socialMedia.size();i++){
                    SocialMedia media = socialMedia.get(i);
                    if(media.getType().equals("YouTube")){
                        id = media.getId();
                    }
                }

                Intent intent = null;
                try {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.google.android.youtube");
                    intent.setData(Uri.parse("https://www.youtube.com/" + id));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/" + id)));
                }


            }
        });

        googleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "";
                ArrayList<SocialMedia> socialMedia = official.getSocialMediaList();
                for(int i = 0;i<socialMedia.size();i++){
                    SocialMedia media = socialMedia.get(i);
                    if(media.getType().equals("GooglePlus")){
                        id = media.getId();
                    }
                }

                Intent intent = null;
                try{
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setClassName("com.google.android.apps.plus", "com.google.android.apps.plus.phone.UrlGatewayActivi ty");
                    intent.putExtra("customAppUri", id);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + id)));
                }
            }
        });

        officialImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentImg = new Intent(OfficalActivity.this, OfficialImgActivity.class);
                intentImg.putExtra("city",txtTitleCity.getText());
                intentImg.putExtra("name", txtOfficialName.getText());
                intentImg.putExtra("role", txtOfficialRole.getText());
                intentImg.putExtra("party", official.getParty());
                intentImg.putExtra("img", official.getImg());
                startActivity(intentImg);
            }
        });
    }

    private void reference(){
        txtTitleCity = findViewById(R.id.txtTitleCity);
        txtOfficialRole = findViewById(R.id.txtOfficialRole);
        txtOfficialName = findViewById(R.id.txtOfficialName);
        txtOfficialParty = findViewById(R.id.txtOfficialParty);
        txtOfficialAddress = findViewById(R.id.txtOfficialAddress);
        txtOfficialPhone = findViewById(R.id.txtOfficialPhone);
        txtOfficialEmai = findViewById(R.id.txtOfficialEmail);
        txtOfficialWeb = findViewById(R.id.txtOfficialWeb);

        officialImg = findViewById(R.id.OfficialImg);
        youtubeImg = findViewById(R.id.imgYoutube);
        googleImg = findViewById(R.id.imgGoogle);
        twitterImg = findViewById(R.id.imgTwitter);
        facebookImg = findViewById(R.id.imgFacebook);
    }

    private void setBackgroundByParty(String party){
        if(party.equals("Democratic Party")){
            getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(OfficalActivity.this,R.color.blue));
        } else if(party.equals("Republican Party")){
            getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(OfficalActivity.this,R.color.red));
        } else if(party.equals("Nonpartisan")){
            getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(OfficalActivity.this,R.color.black));
        }
    }

    private void setSocialMedia(Delegate official){
        ArrayList<SocialMedia> list = official.getSocialMediaList();
        if(list != null){
            for(int i = 0;i<list.size();i++){
                SocialMedia socialMedia = list.get(i);
                if(socialMedia.getType().equals("Facebook")){
                    facebookImg.setVisibility(View.VISIBLE);
                } else if(socialMedia.getType().equals("Twitter")){
                    twitterImg.setVisibility(View.VISIBLE);
                } else if(socialMedia.getType().equals("GooglePlus")){
                    googleImg.setVisibility(View.VISIBLE);
                } else if(socialMedia.getType().equals("YouTube")){
                    youtubeImg.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void setContent(Delegate official){
        txtOfficialRole.setText(official.getNameRole());
        txtOfficialName.setText(official.getName());
        txtOfficialParty.setText(official.getParty());

        setBackgroundByParty(official.getParty());

        if(official.getAddress().equals("")){
            txtOfficialAddress.setText("No data provided");
        } else{
            txtOfficialAddress.setText(official.getAddress());
        }

        if(official.getPhone().equals("")){
            txtOfficialPhone.setText("No data provided");
        } else{
            txtOfficialPhone.setText(official.getPhone());
        }

        if(official.getEmail().equals("")){
            txtOfficialEmai.setText("No data provided");
        } else{
            txtOfficialEmai.setText(official.getEmail());
        }

        if(official.getWebsite().equals("")){
            txtOfficialWeb.setText("No data provided");
        } else{
            txtOfficialWeb.setText(official.getWebsite());
        }

        if(official.getImg().equals("") == false){
            Picasso picasso = new Picasso.Builder(OfficalActivity.this).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    final String changedUrl = official.getImg().replace("http:", "https:");
                    picasso.load(changedUrl)
                            .error(R.drawable.broken)
                            .placeholder(R.drawable.placeholder)
                            .into(officialImg);
                }
            }).build();

            picasso.load(official.getImg())
                    .error(R.drawable.broken)
                    .placeholder(R.drawable.placeholder)
                    .into(officialImg);
        }

        setSocialMedia(official);

    }
}