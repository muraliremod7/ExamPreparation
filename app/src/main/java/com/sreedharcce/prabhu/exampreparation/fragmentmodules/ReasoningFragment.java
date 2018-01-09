package com.sreedharcce.prabhu.exampreparation.fragmentmodules;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import com.sreedharcce.prabhu.exampreparation.R;
import com.sreedharcce.prabhu.exampreparation.adapters.ChoicesAdapter;
import com.sreedharcce.prabhu.exampreparation.model.ChoicesModel;
import com.sreedharcce.prabhu.exampreparation.model.QuestionsModel;
import com.sreedharcce.prabhu.exampreparation.model.TabsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class ReasoningFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public GridView gridView;
    public ArrayList<TabsModel> models = new ArrayList<>();
    public static List<QuestionsModel> mQuestionsCopy;
    public static int CurrentQuestionId = 0;
    private TextView qId, direction, question,opt1,opt2,opt3,opt4,opt5;
    private ImageView questionImage, directionImage,optimage1,optimage2,optimage3,optimage4,optimage5;
    private WebView webView;
    private int start;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout layout1,layout2,layout3,layout4,layout5;
    private OnFragmentInteractionListener mListener;
    private MenuItem getexams, forword;

    public ReasoningFragment() {
        // Required empty public constructor
    }
    public static Fragment getInstance(String nn, int position){
        ReasoningFragment fragment = new ReasoningFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("title",nn);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reasoning, container, false);
        mQuestionsCopy = new ArrayList<>();
            int position = getArguments().getInt("position");
            String title = getArguments().getString("title");

        qId = (TextView) view.findViewById(R.id.qIddd);
        question = (TextView)view.findViewById(R.id.questionnn);
        direction = (TextView)view.findViewById(R.id.question_directionnn);
        questionImage = (ImageView)view.findViewById(R.id.image_questionnn);
        directionImage = (ImageView)view.findViewById(R.id.image_directionnn);
        webView = (WebView)view.findViewById(R.id.webbb);

        ImageView  lef = (ImageView) view.findViewById(R.id.imgleft);
        lef.setOnClickListener(this);
        ImageView righ = (ImageView) view.findViewById(R.id.imgright);
        righ.setOnClickListener(this);

        opt1 = (TextView) view.findViewById(R.id.opt1);
        opt2 = (TextView) view.findViewById(R.id.opt2);
        opt3 = (TextView) view.findViewById(R.id.opt3);
        opt4 = (TextView) view.findViewById(R.id.opt4);
        opt5 = (TextView) view.findViewById(R.id.opt5);

        optimage1 = (ImageView) view.findViewById(R.id.choi1_image);
        optimage2 = (ImageView) view.findViewById(R.id.choi2_image);
        optimage3 = (ImageView) view.findViewById(R.id.choi3_image);
        optimage4 = (ImageView) view.findViewById(R.id.choi4_image);
        optimage5 = (ImageView) view.findViewById(R.id.choi5_image);

        layout1 = (LinearLayout)view.findViewById(R.id.layout1);
        layout1.setOnClickListener(this);
        layout2 = (LinearLayout)view.findViewById(R.id.layout2);
        layout2.setOnClickListener(this);
        layout3 = (LinearLayout)view.findViewById(R.id.layout3);
        layout3.setOnClickListener(this);
        layout4 = (LinearLayout)view.findViewById(R.id.layout4);
        layout4.setOnClickListener(this);
        layout5 = (LinearLayout)view.findViewById(R.id.layout5);
        layout5.setOnClickListener(this);
            getQues(title);
        return view;
    }

    public void getQues(final String title) {
        Ion.with(getContext())
                .load("https://sreedharscce.com/apis/getpaper.php?examname=IBPS%20RRB%20Clerks-VI%20MAINS&examdes=IBPS%20RRB%20Clerks-VI%20MAINS%20-%20Model%20Test%2060&examid=8087")
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
                                    TabsModel tabsModel = new TabsModel();
                                    JSONObject object = array.getJSONObject(i);
                                    tabsModel.setMid(object.getString("mid"));
                                    tabsModel.setMname(object.getString("mname"));
                                    tabsModel.setMnoofqu(object.getString("mnoofq"));
                                    tabsModel.setMqfrom(object.getString("mqfrom"));
                                    tabsModel.setMqto(object.getString("mqto"));
                                    tabsModel.setMduration(object.getString("mduration"));
                                    tabsModel.setMqpsmarks(object.getString("mpsvmarks"));
                                    tabsModel.setMqngmarks(object.getString("mnegmarks"));
                                    JSONArray array1 = object.getJSONArray("questions");
                                    for(int q = 0;q<array1.length();q++){
                                        QuestionsModel questionsModel=new QuestionsModel();
                                        //gets each JSON object within the JSON array
                                        JSONObject object1 = array1.getJSONObject(q);
                                        String questionNumber = object1.getString("qno");
                                        String answer = object1.getString("answer");
                                        String questionDirection = object1.getString("direction");
                                        String questionn = object1.getString("question");
                                        questionsModel.setChoice1(object1.getString("choice1"));
                                        questionsModel.setChoice2(object1.getString("choice2"));
                                        questionsModel.setChoice3(object1.getString("choice3"));
                                        questionsModel.setChoice4(object1.getString("choice4"));
                                        questionsModel.setChoice5(object1.getString("choice5"));
                                        questionsModel.setQnum(questionNumber);
                                        questionsModel.setmQuestionDirection(questionDirection);
                                        questionsModel.setmQuestion(questionn);
                                        questionsModel.setManswer(answer);
                                        mQuestionsCopy.add(questionsModel);

                                    }
                                        models.add(tabsModel);
                                }
                                CurrentQuestionId = 0;
                                loadNextQuestion(0);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }
                });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgleft:
                layout1.setBackgroundDrawable( null);
                layout2.setBackgroundDrawable( null);
                layout3.setBackgroundDrawable( null);
                layout4.setBackgroundDrawable( null);
                layout5.setBackgroundDrawable( null);
                if (CurrentQuestionId > 0) {
                    loadPreviousQuestion(CurrentQuestionId - 1);
                    Log.v("sai", "Current Back QuestionID----" + CurrentQuestionId);
                    CurrentQuestionId--;
                    int op = CurrentQuestionId;
                    //adapter.setPositionSelected(op);
                } else {
                    Toast.makeText(getActivity(), "No Questions", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgright:
                layout1.setBackgroundDrawable( null);
                layout2.setBackgroundDrawable( null);
                layout3.setBackgroundDrawable( null);
                layout4.setBackgroundDrawable( null);
                layout5.setBackgroundDrawable( null);
                if (CurrentQuestionId <= mQuestionsCopy.size() - 2) {
                    loadNextQuestion(CurrentQuestionId + 1);
                    Log.v("sai", "Current Next Question ID------" + CurrentQuestionId);
                    CurrentQuestionId++;
                    int op = CurrentQuestionId;
                    //adapter.setPositionSelected(op);
                    int oq = CurrentQuestionId+1;
                } else {
                    Toast.makeText(getActivity(), "No Questions", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.layout1:
                final int sdk1 = android.os.Build.VERSION.SDK_INT;
                if(sdk1 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layout1.setBackgroundDrawable( getResources().getDrawable(R.drawable.spinner_custom) );
                    layout2.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                } else {
                    layout2.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                    layout1.setBackground( getResources().getDrawable(R.drawable.spinner_custom));
                }
                break;
            case R.id.layout2:
                final int sdk2 = android.os.Build.VERSION.SDK_INT;
                if(sdk2 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layout1.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                    layout2.setBackgroundDrawable( getResources().getDrawable(R.drawable.spinner_custom) );
                } else {
                    layout1.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                    layout2.setBackground( getResources().getDrawable(R.drawable.spinner_custom));
                }
                break;
            case R.id.layout3:
                final int sdk3 = android.os.Build.VERSION.SDK_INT;
                if(sdk3 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layout3.setBackgroundDrawable( getResources().getDrawable(R.drawable.spinner_custom) );
                    layout1.setBackgroundDrawable( null);
                    layout2.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                } else {
                    layout1.setBackgroundDrawable( null);
                    layout2.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                    layout3.setBackground( getResources().getDrawable(R.drawable.spinner_custom));
                }
                break;
            case R.id.layout4:
                final int sdk4 = android.os.Build.VERSION.SDK_INT;
                if(sdk4 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layout1.setBackgroundDrawable( null);
                    layout2.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( getResources().getDrawable(R.drawable.spinner_custom) );
                } else {
                    layout1.setBackgroundDrawable( null);
                    layout2.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                    layout5.setBackgroundDrawable( null);
                    layout4.setBackground( getResources().getDrawable(R.drawable.spinner_custom));
                }
                break;
            case R.id.layout5:
                final int sdk5 = android.os.Build.VERSION.SDK_INT;
                if(sdk5 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layout5.setBackgroundDrawable( getResources().getDrawable(R.drawable.spinner_custom) );
                    layout1.setBackgroundDrawable( null);
                    layout2.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                } else {
                    layout1.setBackgroundDrawable( null);
                    layout2.setBackgroundDrawable( null);
                    layout4.setBackgroundDrawable( null);
                    layout3.setBackgroundDrawable( null);
                    layout5.setBackground( getResources().getDrawable(R.drawable.spinner_custom));
                }
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void loadQuestion(int i) {
        if (i >= 0 || i <= mQuestionsCopy.size()) {

            QuestionsModel quetionClass = mQuestionsCopy.get(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000,600);
            qId.setText(quetionClass.getQnum());
            try{
                if (quetionClass.getmQuestionDirection().endsWith(".jpg")) {
                    directionImage.setVisibility(View.VISIBLE);
                    direction.setVisibility(GONE);
                    webView.setVisibility(GONE);
                    String url = quetionClass.getmQuestionDirection();
                    if(url==null){

                    }else{
                        Picasso.with(getContext())
                                .load(url)
                                .fit()
                                .into(directionImage);
                        directionImage.setLayoutParams(layoutParams);
                    }
                } else {
                    if(quetionClass.getmQuestionDirection().equals("")){
                        direction.setVisibility(GONE);
                    }else {
                        directionImage.setVisibility(GONE);
                        direction.setVisibility(View.VISIBLE);
                        start = quetionClass.getmQuestionDirection().indexOf("<table");
                        if (start < 0){
                            webView.setVisibility(GONE);
                            direction.setText(Html.fromHtml("\n" +"<b>Direction : </b>"+"\n"+ quetionClass.getmQuestionDirection()));
                        }
                        else {
                            webView.setVisibility(View.VISIBLE);
                            webView.loadData("<html><body>" + quetionClass.getmQuestionDirection() + "</body></html>", "text/html", "utf-8");
                        }
                    }

                }
                if(quetionClass.getmQuestion().endsWith(".jpg")){
                    questionImage.setVisibility(View.VISIBLE);
                    question.setVisibility(GONE);
                    String url = "https://sreedharscce.com/android/8656/"+quetionClass.getmQuestion();
                    Picasso.with(getContext())
                            .load(url)
                            .fit()
                            .into(questionImage);
                    questionImage.setLayoutParams(layoutParams);
                }else{
                    question.setText(Html.fromHtml("<b>Question : </b>"+"<br>"+quetionClass.getmQuestion()));
                }
                if(quetionClass.getChoice1().endsWith(".jpg")||quetionClass.getChoice1().contains("https://sreedharscce.com/cgi-bin/mimetex.cgi?")){
                    String url = quetionClass.getChoice1();
                    opt1.setText("(1)");
                    optimage1.setVisibility(View.VISIBLE);
                    Picasso.with(getContext())
                            .load(url)
                            .resize(100,100)
                            .skipMemoryCache()
                            .into(optimage1);
                }else{
                    optimage1.setVisibility(GONE);
                    opt1.setText( "(1)  " + Html.fromHtml(quetionClass.getChoice1()));
                }
                if(quetionClass.getChoice2().endsWith(".jpg")||quetionClass.getChoice2().contains("https://sreedharscce.com/cgi-bin/mimetex.cgi?")){
                    String url = quetionClass.getChoice2();
                    opt2.setText("(2)");
                    optimage2.setVisibility(View.VISIBLE);
                    Picasso.with(getContext())
                            .load(url)
                            .resize(100,100)
                            .skipMemoryCache()
                            .into(optimage2);
                }else{
                    optimage2.setVisibility(GONE);
                    opt2.setText("(2)  " + Html.fromHtml(quetionClass.getChoice2()));
                }
                if(quetionClass.getChoice3().endsWith(".jpg")||quetionClass.getChoice3().contains("https://sreedharscce.com/cgi-bin/mimetex.cgi?")){
                    String url = quetionClass.getChoice3();
                    optimage3.setVisibility(View.VISIBLE);
                    opt3.setText("(3)");
                    Picasso.with(getContext())
                            .load(url)
                            .resize(100,100)
                            .skipMemoryCache()
                            .into(optimage3);
                }else{
                    optimage3.setVisibility(GONE);
                    opt3.setText("(3)  " + Html.fromHtml(quetionClass.getChoice3()));
                }
                if(quetionClass.getChoice4().endsWith(".jpg")||quetionClass.getChoice4().contains("https://sreedharscce.com/cgi-bin/mimetex.cgi?")){
                    String url = quetionClass.getChoice4();
                    opt4.setText("(4)");
                    optimage4.setVisibility(View.VISIBLE);
                    Picasso.with(getContext())
                            .load(url)
                            .resize(100,100)
                            .skipMemoryCache()
                            .into(optimage4);
                }else{
                    optimage4.setVisibility(GONE);
                    opt4.setText("(4)  " + Html.fromHtml(quetionClass.getChoice4()));
                }
                if(quetionClass.getChoice5().endsWith(".jpg")||quetionClass.getChoice5().contains("https://sreedharscce.com/cgi-bin/mimetex.cgi?")){
                    String url = quetionClass.getChoice5();
                    optimage5.setVisibility(View.VISIBLE);
                    opt5.setText("(5)");
                    Picasso.with(getContext())
                            .load(url)
                            .resize(100,100)
                            .skipMemoryCache()
                            .into(optimage5);
                }else{
                    optimage5.setVisibility(GONE);
                    opt5.setText( "(5)  " + Html.fromHtml(quetionClass.getChoice5()));
                }


            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    public void loadNextQuestion(int i) {
        loadQuestion(i);
    }

    public void loadPreviousQuestion(int i) {
        loadQuestion(i);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.exam_menu, menu);
        getexams = menu.findItem(R.id.getexammenu);
        forword = menu.findItem(R.id.forword);
        super.onCreateOptionsMenu(menu,inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.getexammenu:
                View forgotLayout = getActivity().findViewById(R.id.qnolayout);
                forgotLayout.setVisibility(View.VISIBLE);
                getexams.setVisible(false);
                forword.setVisible(true);
                break;
            case R.id.forword:
                View forgotLayoutt = getActivity().findViewById(R.id.qnolayout);
                forgotLayoutt.setVisibility(View.GONE);
                getexams.setVisible(true);
                forword.setVisible(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
