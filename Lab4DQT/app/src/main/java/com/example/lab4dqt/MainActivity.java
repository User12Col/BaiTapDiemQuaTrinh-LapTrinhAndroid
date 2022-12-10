package com.example.lab4dqt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView txtCity, txtSearch;
    Button btnSearch, btnExit;
    ListView list;
    String cityName = "";
    String state = "";
    String zip = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference();


    }

    private void reference(){
        txtCity = findViewById(R.id.txtCity);
        list = findViewById(R.id.listMan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuSearch){
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_search);
            dialog.show();
            btnSearch = dialog.findViewById(R.id.btnSearch);
            btnExit = dialog.findViewById(R.id.btnExit);
            txtSearch = dialog.findViewById(R.id.inputCity);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyDiltpTT9mtJ2Wn53vZN6cVovpuoPJMdGM&address="+txtSearch.getText().toString();
                    getJson(url);

                    dialog.hide();
                }
            });

            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.hide();
                }
            });

        } else if(item.getItemId() == R.id.menuAbout){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void getJson(String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    ArrayList<Delegate> listDelegate = new ArrayList<Delegate>();
                                    JSONObject normalizedInput = response.getJSONObject("normalizedInput");
                                    cityName = normalizedInput.getString("city");
                                    state = normalizedInput.getString("state");
                                    zip = normalizedInput.getString("zip");


                                    JSONArray officesArray = response.getJSONArray("offices");
                                    JSONArray officialsArray = response.getJSONArray("officials");
                                    for(int i = 0; i<officialsArray.length();i++){
                                        JSONObject officialObject = officialsArray.getJSONObject(i);

                                        for(int j = 0; j < officesArray.length();j++){
                                            JSONObject officeObject = officesArray.getJSONObject(j);
                                            JSONArray officialIndicesArray = officeObject.getJSONArray("officialIndices");

                                            for(int k = 0; k<officialIndicesArray.length();k++){
                                                int index = officialIndicesArray.getInt(k);
                                                if(i == index){
                                                    String name = officialObject.getString("name");
                                                    String nameRole = officeObject.getString("name");
                                                    String party = officialObject.getString("party");
                                                    String address = "";
                                                    String phone = "";
                                                    String email = "";
                                                    String website = "";
                                                    String img = "";
                                                    ArrayList<SocialMedia> socialMediaList = new ArrayList<>();

                                                    try{
                                                        JSONArray addressArray = officialObject.getJSONArray("address");
                                                        JSONObject addressOject = addressArray.getJSONObject(0);
                                                        address = addressOject.getString("line1") +", "+addressOject.getString("city")+", "+addressOject.getString("state")+", "+addressOject.getString("zip");

                                                    } catch (JSONException e){
                                                        address = "";
                                                    }

                                                    try{
                                                        JSONArray phoneArray = officialObject.getJSONArray("phones");
                                                        phone = phoneArray.getString(0);
                                                    } catch(JSONException e){
                                                        phone = "";
                                                    }

                                                    try{
                                                        JSONArray emailArray = officialObject.getJSONArray("emails");
                                                        email = emailArray.getString(0);
                                                    } catch(JSONException e){
                                                        email = "";
                                                    }

                                                    try{
                                                        JSONArray webArray = officialObject.getJSONArray("urls");
                                                        website = webArray.getString(0);
                                                    } catch(JSONException e){
                                                        website = "";
                                                    }

                                                    try{
                                                        img = officialObject.getString("photoUrl");
                                                    } catch(JSONException e){
                                                        img = "";
                                                    }

                                                    try {
                                                        JSONArray socialMediaArray = officialObject.getJSONArray("channels");
                                                        for(int l = 0;l<socialMediaArray.length();l++){
                                                            JSONObject socialMediaObject = socialMediaArray.getJSONObject(l);
                                                            String type = socialMediaObject.getString("type");
                                                            String id = socialMediaObject.getString("id");
                                                            SocialMedia socialMedia = new SocialMedia(type,id);
                                                            socialMediaList.add(socialMedia);
                                                        }
                                                    } catch (JSONException e){
                                                        socialMediaList.clear();
                                                    }

                                                    Delegate delegate = new Delegate(name, nameRole, party, address, phone, email, website, img,socialMediaList);
                                                    listDelegate.add(delegate);


                                                }
                                            }

                                        }
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtCity.setText(cityName +", "+state+", "+zip);
                                            DelegateAdapter adapter = new DelegateAdapter(MainActivity.this, R.layout.layout_item,listDelegate);
                                            list.setAdapter(adapter);
                                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                    Delegate a = listDelegate.get(i);
                                                    Intent intent = new Intent(MainActivity.this, OfficalActivity.class);
                                                    intent.putExtra("city",txtCity.getText());
                                                    intent.putExtra("officalObject", a);
                                                    startActivity(intent);

                                                }
                                            });
                                        }
                                    });

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtCity.setText("Cannt find city");

                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        }).start();
    }


}