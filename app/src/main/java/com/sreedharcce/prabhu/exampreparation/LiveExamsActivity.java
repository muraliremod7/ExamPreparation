package com.sreedharcce.prabhu.exampreparation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sreedharcce.prabhu.exampreparation.adapters.SpinnerAdapter;
import com.sreedharcce.prabhu.exampreparation.model.SpinnerModelclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LiveExamsActivity extends AppCompatActivity {
    private ListView listView;
    private SpinnerAdapter spinnerAdapter;
    private ArrayList<SpinnerModelclass> modelclasses;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_exams);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Live Exams");
        listView = (ListView)findViewById(R.id.livewexamslistview);
        modelclasses = new ArrayList<>();
        spinnerAdapter = new SpinnerAdapter(this,modelclasses);
        listView.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged();
        getliveExamslist();
    }

    private void getliveExamslist() {
        Ion.with(getApplicationContext())
                .load("https://sreedharscce.com/apis/getexams.php")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                JSONArray array = jsonObject.getJSONArray("exams");
                                for(int i=0;i<array.length();i++){
                                    SpinnerModelclass spinnerModelclass = new SpinnerModelclass();
                                    JSONObject object = array.getJSONObject(i);
                                    spinnerModelclass.setBname(object.getString("examname"));
                                    modelclasses.add(spinnerModelclass);
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            spinnerAdapter = new SpinnerAdapter(LiveExamsActivity.this,modelclasses);
                            listView.setAdapter(spinnerAdapter);
                            spinnerAdapter.notifyDataSetChanged();

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String examname = ((TextView)view.findViewById(R.id.brname)).getText().toString();

                                    Intent intent = new Intent(LiveExamsActivity.this,SheduledExamsActivity.class);
                                    intent.putExtra("liveexam",examname);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }
}
