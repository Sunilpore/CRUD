package com.example.sunil.sqlliteeg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mname,mage,mid;
    Button madd,mview,mflash,mupdate,mdelete;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mname= (TextView) findViewById(R.id.tv_name);
        mage= (TextView) findViewById(R.id.tv_age);
        mid= (TextView) findViewById(R.id.tv_id);

        madd= (Button) findViewById(R.id.add);
        mview= (Button) findViewById(R.id.view);
        mflash= (Button) findViewById(R.id.flash);
        mupdate= (Button) findViewById(R.id.update);
        mdelete= (Button) findViewById(R.id.delete);

        madd.setOnClickListener(this);
        mview.setOnClickListener(this);
        mflash.setOnClickListener(this);
        mupdate.setOnClickListener(this);
        mdelete.setOnClickListener(this);


        mContext=this;


    }

    @Override
    public void onClick(View view) {

        DatabaseHandler db=new DatabaseHandler(this);
        String name=mname.getText().toString();
        String age= mage.getText().toString();
        String id= mid.getText().toString();

        switch(view.getId())
        {
            case R.id.add:

                if(name.length()!=0 && age.length()!=0)
                {
                    boolean inserted= db.addData( new Model(name,Integer.parseInt(age)) );
                    if(inserted)
                        Toast.makeText(MainActivity.this, "Details are added", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Details are not added", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Fill Name or Age field", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.view:
                Intent i=new Intent(MainActivity.this,Home.class);
                startActivity(i);
                finish();
                break;

            case R.id.flash:
                Cursor res=db.getAllData();

                if(res.getCount()==0){
                    showMsg("ERROR","DATA NOT FOUND");
                }
                else{
                    StringBuffer buf=new StringBuffer();

                    while(res.moveToNext()){
                        buf.append("ID:"+res.getString(0)+"\n");
                        buf.append("Name:"+res.getString(1)+"\n");
                        buf.append("Age:"+res.getString(2)+"\n");
                        buf.append("Checkbox:"+res.getString(3)+"\n\n");

                    }
                    showMsg("Details",buf.toString());
                }
                break;

            case R.id.update:


                if(name.length()!=0 && age.length()!=0 && id.length()!=0)
                {
                    boolean isUpdate = db.updateData(new Model(id, name, Integer.parseInt(age)));
                    if (isUpdate)
                        Toast.makeText(MainActivity.this, "Details are updated", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Plz,Kindly Enter the Name and Age too", Toast.LENGTH_LONG).show();
                break;

            case R.id.delete:
                int isDelete=db.deleteData(new Model(id));
                if(id.length()!=0) {
                    if (isDelete == 0)
                        Toast.makeText(MainActivity.this, "Delete Unsuccessful", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Please,Enter the ID no. to Delete", Toast.LENGTH_LONG).show();
                break;
        }

    }

    public void showMsg(String title,String msg){
        AlertDialog.Builder bld=new AlertDialog.Builder(this);
        bld.setTitle(title);
        bld.setMessage(msg);
        bld.show();
        bld.setCancelable(true);

    }

}