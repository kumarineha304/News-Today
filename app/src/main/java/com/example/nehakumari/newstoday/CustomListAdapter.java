package com.example.nehakumari.newstoday;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nehakumari.newstoday.Model.News_Data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by NEHA KUMARI on 01-03-2018.
 */

public class CustomListAdapter extends ArrayAdapter<News_Data> {

    private final Activity context;
    private List<News_Data> news_dataList;
    private LayoutInflater inflater;
//    private final Integer[] imageIDArray;
//    private final String[] nameArray;
//    private final String[] infoArray;
/*
,String[] nameArrayparam,String[] infoArrayparam,Integer[] imageIDArrayparam
 */
    public CustomListAdapter(Activity context, List<News_Data> news_dataList){

        super(context,R.layout.list_view_row,news_dataList);
        this.context = context;
        this.news_dataList = news_dataList;
        this.inflater = LayoutInflater.from(context);

//        super(context,R.layout.list_view_row,nameArrayparam);
//
//        this.context=context;
//        this.nameArray=nameArrayparam;
//        this.imageIDArray=imageIDArrayparam;
//        this.infoArray=infoArrayparam;
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
        TextView dateTextField = (TextView)rowView.findViewById(R.id.dateTextViewID);
        ImageView imgView = (ImageView)rowView.findViewById(R.id.imageView1ID);

        News_Data data = news_dataList.get(position);
        nameTextField.setText(data.getTitle());
        infoTextField.setText(data.getAuthor());
        dateTextField.setText(data.getpublishedAt());
        //imgView.setImageResource();/**/
        Picasso.with(context)
                .load(data.geturlToImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgView);

        return rowView;
    }

}
