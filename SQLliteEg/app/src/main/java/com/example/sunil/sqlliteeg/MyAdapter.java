package com.example.sunil.sqlliteeg;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sunil on 11/2/2017.
 */

class MyAdapter extends BaseAdapter {

    public String updateCb;
    private Context mContext;
    private ArrayList<Model> mList;
    LayoutInflater inflater;
    UpdateListener onUpdateListener;
    DatabaseHandler db;

    public MyAdapter(Context mContext, ArrayList<Model> list) {
        this.mContext=mContext;
        this.mList =list;
        inflater=LayoutInflater.from(mContext);
        db=new DatabaseHandler(mContext);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Model getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup vg) {
        ViewHolder vh;
        if(view==null){
            vh=new ViewHolder();
            view=LayoutInflater.from(mContext).inflate(R.layout.lay3,vg,false);
            vh.mtv1=view.findViewById(R.id.tv1);
            vh.mtv2=view.findViewById(R.id.tv2);
            vh.mcb=view.findViewById(R.id.cb);
            view.setTag(vh);

        }
        else{
            vh= (ViewHolder) view.getTag();
        }

        Model current= getItem(i);

        vh.mtv1.setText(current.name);
        vh.mtv2.setText(String.valueOf(current.newage));
        vh.mcb.setTag(current);
        vh.mcb.setChecked(current.isSelected());

        vh.mcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ch= (CheckBox) view;
                Log.d("TAG", "First ; " + ch.isChecked());
                Model tmp= (Model) ch.getTag();
                Log.d("TAG", "before : " + tmp.isSelected());
                tmp.setSelected(ch.isChecked());
                Log.d("TAG", "after : " + tmp.isSelected());
                notifyDataSetChanged();

                boolean updatestatus=db.checkboxUpdater(tmp);
                onUpdateListener.onUpdateListenernow(updatestatus,i);

                /*boolean isCbupdate=db.checkboxUpdater(tmp);
                if(isCbupdate)
                    Toast.makeText(mContext,"Tickmark is saved",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(mContext, "Tickmark is not saved", Toast.LENGTH_LONG).show();*/
            }
        });

       /* String updateCb=String.valueOf(ch.isChecked());
            boolean isCbupdate=db.checkboxUpdater(new Model(updateCb));
            if(isCbupdate==true)
                Toast.makeText(mContext,"Tickmark is saved",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mContext, "Tickmark is not saved", Toast.LENGTH_LONG).show();*/




        /*vh.mcb.setOnClickListener(new View.OnClickListener() {   //This method is use without 'Model tmp= (Model) ch.getTag();'
            @Override
            public void onClick(View view) {

                CheckBox ch= (CheckBox) view;
                Model tmp= (Model) getItem(i);
                tmp.setSelected(ch.isChecked());
                notifyDataSetChanged();
            }
        });
*/        /*vh.mcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Model tmp=(Model) getItem(i);
                tmp.setSelected(b);
                notifyDataSetChanged();
            }
        });*/


        return view;
    }

    private class ViewHolder{
        TextView mtv1,mtv2;
        CheckBox mcb;
    }

    public void setOnItemListener(UpdateListener onUpdateListener){
        this.onUpdateListener=onUpdateListener;
    }

}