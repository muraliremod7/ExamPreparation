package com.sreedharcce.prabhu.exampreparation.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sreedharcce.prabhu.exampreparation.ExamActivity;
import com.sreedharcce.prabhu.exampreparation.R;
import com.sreedharcce.prabhu.exampreparation.model.SheduledTestModel;

import java.util.List;

/**
 * Created by Prabhu on 02-11-2017.
 */

public class SheduledExamsAdapter extends RecyclerView.Adapter<SheduledExamsAdapter.MyViewHolder>{

    private Context mContext;
    private List<SheduledTestModel> albumList;
    SharedPreferences.Editor editor;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView examName, examDesc, examDate, examQuestions, examDuration, examMarks;
        public Button startExam;

        public MyViewHolder(View view) {
            super(view);
            examName = (TextView)view.findViewById(R.id.examname);
            examDesc = (TextView) view.findViewById(R.id.examdesc);
            examDate = (TextView) view.findViewById(R.id.examdate);
            examQuestions = (TextView) view.findViewById(R.id.examquestions);
            examDuration = (TextView) view.findViewById(R.id.examduration);
            examMarks = (TextView) view.findViewById(R.id.exammarks);
            startExam = (Button)view.findViewById(R.id.startexam);
        }
    }
    public SheduledExamsAdapter(Context mContext, List<SheduledTestModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SheduledTestModel testModel = albumList.get(position);
        holder.examName.setText(testModel.getExamName());
        holder.examDesc.setText(testModel.getExamDesc());
        holder.examDate.setText(testModel.getExamdate());
        holder.examQuestions.setText(testModel.getTotalque());
        holder.examDuration.setText(testModel.getTotaldur());
        holder.examMarks.setText(testModel.getTotalmarks());
        holder.startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ExamActivity.class);
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
                editor = settings.edit();
                editor.putString("examid", testModel.getExamId());
                editor.putString("examname",testModel.getExamName());
                editor.putString("examdesc",testModel.getExamDesc());
                editor.commit();
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
