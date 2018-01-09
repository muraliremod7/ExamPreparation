package com.sreedharcce.prabhu.exampreparation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sreedharcce.prabhu.exampreparation.R;
import com.sreedharcce.prabhu.exampreparation.model.SpinnerModelclass;

import java.util.ArrayList;

/**
 * Created by Prabhu on 14-10-2017.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {
    public ArrayList modelclasses;
    public Context context;
    public LayoutInflater flater;
    public SpinnerAdapter( Context context, ArrayList modelclasses) {
        super(context,  R.layout.spinneritem, modelclasses);
        this.context = context;
        this.modelclasses = modelclasses;
        flater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(final int position, View convertView, ViewGroup parent) {
        View row = flater.inflate(R.layout.spinneritem, parent, false);
        TextView brname = (TextView)row.findViewById(R.id.brname);
        TextView bracr = (TextView)row.findViewById(R.id.bracr);
        TextView brid = (TextView)row.findViewById(R.id.brid);
        SpinnerModelclass modelclass = (SpinnerModelclass) modelclasses.get(position);

        brname.setText(modelclass.getBname());
        bracr.setText(modelclass.getBacr());
        brid.setText(modelclass.getBid());

        return row;
    }
}
