package com.sreedharcce.prabhu.exampreparation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sreedharcce.prabhu.exampreparation.adapters.SheduledExamsAdapter;
import com.sreedharcce.prabhu.exampreparation.model.SheduledTestModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SheduledExamsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SheduledExamsAdapter adapter;
    private List<SheduledTestModel> testModels;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheduled_exams);
        if(getSupportActionBar()==null){
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        testModels = new ArrayList<>();
        adapter = new SheduledExamsAdapter(this, testModels);
        Intent intent = getIntent();
        String examname = intent.getStringExtra("liveexam").replace(" ","%20");
        getSupportActionBar().setTitle(examname.replace("%20"," "));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getExams(examname);
    }

    private void getExams(String examname) {
        Ion.with(getApplicationContext())
                .load("https://sreedharscce.com/apis/getschedules.php?examname="+examname)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try{
                                JSONObject jsonObject = new JSONObject(result);
                                JSONArray array = jsonObject.getJSONArray("schedules");
                                for(int i=0;i<array.length();i++){
                                    SheduledTestModel testModel = new SheduledTestModel();
                                    JSONObject object = array.getJSONObject(i);
                                    testModel.setExamName(object.getString("examname"));
                                    testModel.setExamId(object.getString("examid"));
                                    testModel.setExamDesc(object.getString("examdesc"));
                                    testModel.setExamdate(object.getString("examdt"));
                                    testModel.setTotalque(object.getString("totques"));
                                    testModel.setTotaldur(object.getString("totduration"));
                                    testModel.setTotalmarks(object.getString("totmarks"));

                                    testModels.add(testModel);
                                }
                                adapter = new SheduledExamsAdapter(SheduledExamsActivity.this, testModels);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }catch (JSONException e1){

                            }
                        }
                    }
                });

    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
