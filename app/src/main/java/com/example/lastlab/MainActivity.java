package com.example.lastlab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private RecyclerView rv;
    public ArrayList<Pair<String, String>> list = new ArrayList<Pair<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, list);
        new Func().execute();
    }

    public class Func extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String[] arg) {
            try {
                Elements images = Jsoup.connect("https://unsplash.com/s/photos/banana").get().select("img");
                for (Element img : images) {
                    list.add(new Pair<String, String>(img.absUrl("src"),img.attr("alt")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            rv.setAdapter(adapter);
        }
    }
}