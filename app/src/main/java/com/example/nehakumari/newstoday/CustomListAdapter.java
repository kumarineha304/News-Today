package com.example.nehakumari.newstoday;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nehakumari.newstoday.Model.News_Data;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*A Custom adapter class to set the values to the list view*/
public class CustomListAdapter extends ArrayAdapter<News_Data> {

    private final Activity context;
    private List<News_Data> news_dataList;
    private LayoutInflater inflater;

    public CustomListAdapter(Activity context,List<News_Data> news_dataList){

        super(context,R.layout.list_view_row,news_dataList);
        this.context = context;
        this.news_dataList = news_dataList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public News_Data getItem(int position){
        return news_dataList.get(position);
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_row,null,true);

        TextView nameTextField = (TextView)rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView)rowView.findViewById(R.id.infoTextViewID);
        ImageView imgView = (ImageView)rowView.findViewById(R.id.imageView1ID);

        News_Data data = news_dataList.get(position);
        //To check if data is null
        if(data.getTitle()!="null")
            nameTextField.setText(data.getTitle());
        else
            nameTextField.setVisibility(View.GONE);

        String des = data.getDescription();
        Log.e("Description ",des);

        if(data.getDescription()!="null")
            infoTextField.setText(data.getDescription());
        else
            infoTextField.setVisibility(View.GONE);


            imgView.setImageResource(R.drawable.placeholder);
            new DownloadImageTask(imgView)
                .execute(data.geturlToImage());

        return rowView;
    }

    /*An async task for processing the image url and to prevent the UI thread from getting blocked*/
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView ImgView;

        public DownloadImageTask(ImageView ImgView) {
            this.ImgView = ImgView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap Img = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                Img = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return Img;
        }

        protected void onPostExecute(Bitmap Img) {
                ImgView.setImageBitmap(Img);
        }
    }


}
