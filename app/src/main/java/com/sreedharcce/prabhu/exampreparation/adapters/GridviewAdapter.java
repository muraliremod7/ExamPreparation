package com.sreedharcce.prabhu.exampreparation.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sreedharcce.prabhu.exampreparation.R;
import com.sreedharcce.prabhu.exampreparation.model.QuestionsModel;

import java.util.ArrayList;

/**
 * Created by Prabhu on 22-10-2017.
 */

public class GridviewAdapter extends BaseAdapter {
    private int PositionSelected = 0;
    private ArrayList<QuestionsModel> models;
    private Context mContext;
    private TextView qno;
    private LayoutInflater inflater;

    // Constructor
    public GridviewAdapter(Context c,ArrayList<QuestionsModel> models) {
        mContext = c;
        this.models = models;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setPositionSelected(int position)
    {
        PositionSelected = position;
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.singlegridview, parent, false);
            qno = (TextView)row.findViewById(R.id.qnumber);
            QuestionsModel questionsModel = models.get(position);
            qno.setText("Q"+ questionsModel.getQnum());
        if(position==PositionSelected){
            row.setBackgroundColor(Color.parseColor("#FF4081"));
            notifyDataSetChanged();
        }else{
        }
        return row;
    }
}
