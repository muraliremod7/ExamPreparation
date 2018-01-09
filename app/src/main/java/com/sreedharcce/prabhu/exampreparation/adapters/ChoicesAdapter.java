package com.sreedharcce.prabhu.exampreparation.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import com.sreedharcce.prabhu.exampreparation.R;
import com.sreedharcce.prabhu.exampreparation.model.ChoicesModel;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Prabhu on 26-10-2017.
 */

public class ChoicesAdapter extends BaseAdapter {
    private ArrayList<String> choicesModels;
    private Context context;
    private LayoutInflater inflater;
    public ChoicesAdapter(Context context,ArrayList<String> choicesModels){
        this.context = context;
        this.choicesModels = choicesModels;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return choicesModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.choicessingleitem, parent, false);

        if(choicesModels.get(position).endsWith(".jpg")){
            ImageView choiceimg = (ImageView)row.findViewById(R.id.choiceimg);
            String url = "https://sreedharscce.com/android/8656/"+choicesModels.get(position);
            Picasso.with(context)
                    .load(url)
                    .resize(100,100)
                    .skipMemoryCache()
                    .into(choiceimg);
        }else{
            TextView choicetext = (TextView)row.findViewById(R.id.choicetext);
            choicetext.setText(choicesModels.get(position));
        }
        return row;
    }
}
