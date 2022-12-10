package com.example.lab3_dqt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SubActivity extends AppCompatActivity {

    TextView txtFoodName;
    ArrayList<Food> listsf;
    ArrayList<String> listTitle;
    ListView listSubFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        reference();
        setTxtFoodName();
        readRss();

    }

    private void reference(){
        txtFoodName = findViewById(R.id.txtFoodName);
        listSubFood = findViewById(R.id.listSubFood);
        listTitle = new ArrayList<String>();

    }

    private void setTxtFoodName(){
        Intent intent = getIntent();
        int i = intent.getIntExtra("numFood",0);
        if(i == 0){
            txtFoodName.setText("Proteins");
        }else if(i == 1){
            txtFoodName.setText("Grains and Starches");
        }else if(i == 2){
            txtFoodName.setText("Vitamins");
        }else if(i == 3){
            txtFoodName.setText("Nutraceuticals");
        }else if(i == 4){
            txtFoodName.setText("Fats and Oils");
        }else if(i == 5){
            txtFoodName.setText("Amino Acids");
        }else if(i == 6){
            txtFoodName.setText("Fibers and Legumes");
        }else if(i == 7){
            txtFoodName.setText("Minerals");
        }else if(i == 8){
            txtFoodName.setText("Processing functional ingredients");
        }else if(i == 9){
            txtFoodName.setText("Preservatives");
        }
    }

    private String getUrl(){
        String url = "";
        Intent intent = getIntent();
        int i = intent.getIntExtra("numFood",0);
        if(i == 0){
            url = "https://www.petfoodindustry.com/rss/topic/292-proteins";
        }else if(i == 1){
            url = "https://www.petfoodindustry.com/rss/topic/294-grains-and-starches";
        }else if(i == 2){
            url = "https://www.petfoodindustry.com/rss/topic/296-vitamins";
        }else if(i == 3){
            url = "https://www.petfoodindustry.com/rss/topic/298-nutraceuticals";
        }else if(i == 4){
            url = "https://www.petfoodindustry.com/rss/topic/300-fats-and-oils";
        }else if(i == 5){
            url = "https://www.petfoodindustry.com/rss/topic/293-amino-acids";
        }else if(i == 6){
            url = "https://www.petfoodindustry.com/rss/topic/295-fibers-and-legumes";
        }else if(i == 7){
            url = "https://www.petfoodindustry.com/rss/topic/297-minerals";
        }else if(i == 8){
            url = "https://www.petfoodindustry.com/rss/topic/299-processing-functional-ingredients";
        }else if(i == 9){
            url = "https://www.petfoodindustry.com/rss/topic/301-preservatives";
        }

        return url;
    }

    protected String getNodeValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        Node node = nodeList.item(0);
        if(node!=null){
            if(node.hasChildNodes()){
                Node child = node.getFirstChild();
                while (child!=null){
                    if(child.getNodeType() == Node.TEXT_NODE || child.getNodeType() == Node.CDATA_SECTION_NODE ){
                        return  child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public void readRss(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    String getUrl = getUrl();
                    URL url = new URL(getUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    listsf = new ArrayList<Food>();
                    InputStream istream = urlConnection.getInputStream();

                    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                    builderFactory.setNamespaceAware(true);
                    builderFactory.setCoalescing(true);
                    DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(istream);
                    NodeList nList = doc.getElementsByTagName("item");
                    NodeList nListImg = doc.getElementsByTagName("media:content");
                    String linkImg = "";
                    for(int i = 0;i<nList.getLength();i++){
                        if(nList.item(0).getNodeType() == Node.ELEMENT_NODE){
                            Element elm = (Element)nList.item(i);

                            for(int j = 0;j<nListImg.getLength();j++){
                                Node node = nListImg.item(j);
                                if(node.getNodeType() == Node.ELEMENT_NODE){
                                    Element ele= (Element) node;
                                    if(j == i){
                                        linkImg = ele.getAttribute("url");
                                    }

                                }
                            }

                            Food food = new Food(getNodeValue("title",elm),
                                    getNodeValue("description",elm),
                                    getNodeValue("pubDate",elm),
                                    getNodeValue("link",elm),
                                    linkImg);
                            listsf.add(food);

                        }
                    }

                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0;i<listsf.size();i++){
                                Food food = listsf.get(i);
                                listTitle.add(food.getTitle());
                            }

                            ArrayAdapter adapter = new ArrayAdapter(SubActivity.this, android.R.layout.simple_list_item_1,listTitle);
                            listSubFood.setAdapter(adapter);

                            listSubFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Dialog dialog = new Dialog(SubActivity.this);
                                    dialog.setContentView(R.layout.dialog_layout);
                                    TextView txtTitle = dialog.findViewById(R.id.txtTitle);
                                    TextView txtDis = dialog.findViewById(R.id.txtDis);
                                    TextView txtImg = dialog.findViewById(R.id.txtImg);
                                    TextView txtDate = dialog.findViewById(R.id.txtDate);
                                    ImageView img = dialog.findViewById(R.id.imgFood);
                                    Button btnMore = dialog.findViewById(R.id.btnMore);
                                    Button btnClose = dialog.findViewById(R.id.btnClose);

                                    Food food = listsf.get(i);
                                    txtTitle.setText(food.getTitle());
                                    txtDis.setText(Html.fromHtml(food.getDescription()));
                                    txtImg.setText(food.getTitle());
                                    txtDate.setText(food.getDate());
                                    Picasso.with(dialog.getContext())
                                            .load(food.getImg())
                                            .placeholder(R.drawable.placeholder)
                                            .into(img);

                                    btnMore.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Uri uri = Uri.parse(food.getLink());
                                            Intent web = new Intent(Intent.ACTION_VIEW,uri);
                                            startActivity(web);
                                        }
                                    });

                                    btnClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.hide();
                                        }
                                    });
                                    dialog.show();
                                }
                            });

                        }
                    });

                }

            }
        }).start();
    }


}