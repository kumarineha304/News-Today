package com.example.nehakumari.newstoday;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.nehakumari.newstoday.Model.News;
import com.example.nehakumari.newstoday.Model.News_Data;
import com.example.nehakumari.newstoday.Model.Source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*The MainActivity class which process the JSON data and parse them and display the list of news to the user*/
public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListAdapter customListAdapter;
    ProgressBar progressBar;
   private List<News_Data> news_dataList;
    private String url = "https://newsapi.org/v2/everything?sources=the-times-of-india&apiKey=b05e734cb63a4ff0934999281cbcdaf0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news_dataList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list_view1);
        progressBar = (ProgressBar)findViewById(R.id.home_progress_bar);

        new GetNews().execute();

           }

    /*
    AsyncTasks to get json data by parsing
     */
    private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonstr = sh.makeServiceCall(url);

            Log.e("MainActivity ", " " + jsonstr);
            if (jsonstr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonstr);
                    String status = jsonObject.getString("status");
                    int totalResults = jsonObject.getInt("totalResults");
                    JSONArray articles = jsonObject.getJSONArray("articles");


                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject a = articles.getJSONObject(i);
                        JSONObject source = a.getJSONObject("source");
                        String id = source.getString("id");
                        String name = source.getString("name");

                        String author = a.getString("author");
                        String title = a.getString("title");
                        String description = a.getString("description");
                        String url = a.getString("url");
                        String urlToImage = a.getString("urlToImage");
                        String publishedAt = a.getString("publishedAt");

                        Source src = new Source(id,name);

                        News_Data news_data = new News_Data(src,author,title,description,url,urlToImage,publishedAt);

                        news_dataList.add(news_data);

                    }
                    News news = new News(status,totalResults,news_dataList);

                } catch (JSONException e) {
                    Log.e("MainActivity ", "JSONExcepition " + e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);

            customListAdapter= new CustomListAdapter(MainActivity.this,news_dataList);
            listView.setAdapter(customListAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(MainActivity.this,Detail_Activity.class);
                    intent.putExtra("url",news_dataList.get(position).getUrl());
                    startActivity(intent);


                }
            });
        }
    }

}
