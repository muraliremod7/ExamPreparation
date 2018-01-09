package com.sreedharcce.prabhu.exampreparation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sreedharcce.prabhu.exampreparation.adapters.SpinnerAdapter;
import com.sreedharcce.prabhu.exampreparation.model.SpinnerModelclass;
import com.sreedharcce.prabhu.exampreparation.services.AlertDialogManager;
import com.sreedharcce.prabhu.exampreparation.services.ConnectionDetector;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
        public EditText  regNo,passWord;
        public Spinner brachesspinner;
    public Button login;
        public AlertDialogManager dialogManager;
        public ConnectionDetector detector;
        public ArrayList<SpinnerModelclass> arrayList = new ArrayList<>();
        public String bracr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regNo = (EditText)findViewById(R.id.login_regno);
        passWord = (EditText)findViewById(R.id.login_password);
        brachesspinner = (Spinner)findViewById(R.id.branchesspinner);
        login = (Button)findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regno = regNo.getText().toString();
                String password = passWord.getText().toString();
                if(regno.equals("")||password.equals("")){
                    dialogManager.showAlertDialog(LoginActivity.this,"Enter RegNo. and Password",false);
                }else if(regno.length()>6){
                    dialogManager.showAlertDialog(LoginActivity.this,"Regno. should be 6 numbers",false);
                }else if(!detector.isNetworkOn(LoginActivity.this)){
                    dialogManager.showAlertDialog1(LoginActivity.this,"No Internet Connection","Do you want on go to settings",false);
                }
                else{
                    login(regno,password,bracr);
                }
            }
        });
        //alertdailog initialization.........
        dialogManager = new AlertDialogManager();

        //connection detector initializing......
        detector = new ConnectionDetector(this);

            getspinnerdata();
    }

    private void getspinnerdata(){
        Ion.with(LoginActivity.this)
                .load("https://sreedharscce.com/apis/getbranches.php")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try {
                                JSONObject j = new JSONObject(result);
                                    JSONArray jsonObject = j.getJSONArray("branches");
                                    for(int i =0;i<jsonObject.length();i++){
                                        SpinnerModelclass modelclass = new SpinnerModelclass();
                                        JSONObject json = jsonObject.getJSONObject(i);
                                        String bid = json.getString("bid");
                                        modelclass.setBid(bid);
                                        String bacr = json.getString("bacr");
                                        modelclass.setBacr(bacr);
                                        String bname=json.getString("bname");
                                        modelclass.setBname(bname);
                                        arrayList.add(modelclass);
                                    }
                                SpinnerAdapter arrayAdapter = new SpinnerAdapter(getApplicationContext(),arrayList);
                                brachesspinner.setAdapter(arrayAdapter);
                                brachesspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        bracr = ((TextView)view.findViewById(R.id.bracr)).getText().toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }
    private void login(String regno, String password, String bracr) {

    }
}
