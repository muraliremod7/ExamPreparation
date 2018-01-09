package com.sreedharcce.prabhu.exampreparation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sreedharcce.prabhu.exampreparation.adapters.GridviewAdapter;
import com.sreedharcce.prabhu.exampreparation.fragmentmodules.ReasoningFragment;
import com.sreedharcce.prabhu.exampreparation.model.ChoicesModel;
import com.sreedharcce.prabhu.exampreparation.model.QuestionsModel;
import com.sreedharcce.prabhu.exampreparation.model.TabsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ExamActivity extends AppCompatActivity  {
    public ImageView qnoleft, qnoright, left, right;
    public GridView gridView;
    public static ArrayList<TabsModel> models = new ArrayList<>();
    public static List<QuestionsModel> mQuestionsCopy = new ArrayList<>();
    public static int CurrentQuestionId = 0;
    private GridviewAdapter adapter;
    private TextView qId;
    private WebView webView;
    private int start;
    private ArrayList<String> answers = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();

    private Toolbar toolbar;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    private final List<String> nooffragments = new ArrayList<>();
    public final static int HOME_TAB = 0;
    public static SharedPreferences exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        exam = PreferenceManager.getDefaultSharedPreferences(this);
        String examid = exam.getString("examid","0").replace(" ","%20");
        String examname = exam.getString("examname","0").replace(" ","%20");
        String examdesc = exam.getString("examdesc","0").replace(" ","%20");
        String title = examdesc.replace("%20"," ");
        toolbar = (Toolbar) findViewById(R.id.examtoolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)findViewById(R.id.toolbarTitle);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
        textView.setSingleLine(true);
        textView.setSelected(true);
        textView.setMarqueeRepeatLimit(-1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.examtabs);
        tabLayout.setupWithViewPager(viewPager);

        getnoofmodules(examid,examname,examdesc);

    }

    private void getnoofmodules(String examid, String examname, String examdesc) {
        Ion.with(getApplicationContext())
                .load("https://sreedharscce.com/apis/getpaper.php?examname="+examname+"&examdes="+examdesc+"&examid="+examid)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                JSONArray array = jsonObject.getJSONArray("modules");
                                for(int i=0;i<array.length();i++){
                                    JSONObject object = array.getJSONObject(i);
                                    nooffragments.add(object.getString("mname"));
                                    tabLayout.addTab(tabLayout.newTab().setText(object.getString("mname")));
                                }
                                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), array);
                                viewPager = (ViewPager) findViewById(R.id.examviewpager);
                                viewPager.setAdapter(adapter);
                                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                                onTabSelectedListener(viewPager);
                                adapter.notifyDataSetChanged();
                                tabLayout.setupWithViewPager(viewPager);
                                tabLayout.setOnTabSelectedListener(onTabSelectedListener(viewPager));

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }
                });
    }
    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager viewPager) {

        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }
    private void getQnos() {
        Ion.with(ExamActivity.this)
                .load("https://sreedharscce.com/android/8656/8656.json")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {

                        } else {
                            try {
                                JSONArray jsonArray = new JSONArray(result);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    QuestionsModel questionsModel = new QuestionsModel();
                                    ChoicesModel choicesModel = new ChoicesModel();

                                    //gets each JSON object within the JSON array
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String questionNumber = jsonObject.getString("qno");
                                    String answer = jsonObject.getString("answer");
                                    String questionDirection = jsonObject.getString("direction");
                                    String questionn = jsonObject.getString("question");
                                    String choice1 = jsonObject.getString("choice1");
                                    String choice2 = jsonObject.getString("choice2");
                                    String choice3 = jsonObject.getString("choice3");
                                    String choice4 = jsonObject.getString("choice4");
                                    String choice5 = jsonObject.getString("choice5");
                                    if (choice1.endsWith(".jpg")) {
                                        list = new ArrayList<>();
                                        list.add(choice1);
                                        list.add(choice2);
                                        list.add(choice3);
                                        list.add(choice4);
                                        list.add(choice5);
                                    } else {
                                        list = new ArrayList<>();
                                        list.add("A) " + choice1);
                                        list.add("B) " + choice2);
                                        list.add("C) " + choice3);
                                        list.add("D) " + choice4);
                                        list.add("E) " + choice5);
                                    }
                                    choicesModel.setChoice(list);
                                    questionsModel.setQnum(questionNumber);
                                    questionsModel.setmQuestionDirection(questionDirection);
                                    questionsModel.setmQuestion(questionn);
                                    questionsModel.setManswer(answer);

                                    questionsModel.setChoices(list);

                                    //models.add(questionsModel);
                                }
//                                adapter = new GridviewAdapter(getActivity(), models);
//                                gridView.setAdapter(adapter);
//                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                        CurrentQuestionId = position;
//                                        viewPager.setCurrentItem(position);
//                                        int op = CurrentQuestionId;
//                                        adapter.setPositionSelected(op);
//                                        pagerAdapter.setPositionSelected(op);
//                                    }
//                                });

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exam_menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
class ViewPagerAdapter extends FragmentPagerAdapter {
    String nn;
    private JSONArray jsonArray;
    public ViewPagerAdapter(FragmentManager manager, JSONArray jsonArray) {
        super(manager);
        this.jsonArray= jsonArray;
    }


    @Override
    public Fragment getItem(int position) {
        try {
            nn = jsonArray.getJSONObject(position).getString("mname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ReasoningFragment.getInstance(nn,position);
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        try {
            nn = jsonArray.getJSONObject(position).getString("mname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nn;
    }
}