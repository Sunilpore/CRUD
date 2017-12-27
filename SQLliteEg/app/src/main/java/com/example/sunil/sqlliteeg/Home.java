package com.example.sunil.sqlliteeg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    DatabaseHandler db;
    Context mContext;
    CoordinatorLayout cdlay;
    UpdateListener onUpdateListener;
    ArrayList<Model> mList;
    MyAdapter adapter;

    ListView lv;
    Button mback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext=this;
        lv= (ListView) findViewById(R.id.lstv);
        mback= (Button) findViewById(R.id.back);
        cdlay= (CoordinatorLayout) findViewById(R.id.cordlay);

        db=new DatabaseHandler(this);
        listDisplay();

        onUpdateListener=new UpdateListener(){

            @Override
            public void onUpdateListenernow(boolean status, int position) {

                int p=position+1;
                if(status){
                    Snackbar sn=Snackbar.make(cdlay,"CheckBox at Position "+p+": Update Successfull",Snackbar.LENGTH_LONG);
                    sn.show();
                }
                else{
                    Snackbar sn=Snackbar.make(cdlay,"CheckBox at Position "+p+": Update failed",Snackbar.LENGTH_LONG);
                    sn.show();
                }
            }
        };

        adapter.setOnItemListener(onUpdateListener);

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Home.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void listDisplay(){

        mList=db.getArrayData();
        adapter=new MyAdapter(this,mList);
        lv.setAdapter(adapter);
    }


    /*private void listDisplay(){

        Cursor res=db.getAllData();

        ArrayList<Model> mList=new ArrayList<Model>();


        while(res.moveToNext()){
            mList.add( new Model(res.getString(0),res.getString(1)+"\n",res.getString(2)+"\n",false));

            MyAdapter adapter=new MyAdapter(this,mList);
            lv.setAdapter(adapter);
        }
    }*/
}
