package com.example.nehakumari.newstoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nehakumari.newstoday.Model.News;
import com.example.nehakumari.newstoday.Model.News_Data;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomListAdapter customListAdapter;
    private List<News_Data> news_dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //news_dataList = new ArrayList<>();

        listView = (ListView)findViewById(R.id.list_view1);

        customListAdapter= new CustomListAdapter(MainActivity.this,new ArrayList<News_Data>());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsApi_Interface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi_Interface api_interface = retrofit.create(NewsApi_Interface.class);

        Call<News> call = api_interface.getNews_Data();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                News news = response.body();
                news_dataList = response.body().getNews_data();
                Log.d("status"," "+news.getStatus());
                Log.d("total results "," "+news.gettotalResults());
                Log.d("articles "," "+news.getNews_data());
                Toast.makeText(getApplicationContext()," on Response "+news.getStatus(),Toast.LENGTH_LONG).show();
                customListAdapter= new CustomListAdapter(MainActivity.this,news_dataList);
                listView.setAdapter(customListAdapter);
                customListAdapter.notifyDataSetChanged();

               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(MainActivity.this,Detail_Activity.class);
                        String message = "this is a detail activity";
                        intent.putExtra("url",news_dataList.get(position).getUrl());
                       startActivity(intent);


                    }
                });
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"onFailure Called",Toast.LENGTH_LONG).show();
            }
        });


    }

}
