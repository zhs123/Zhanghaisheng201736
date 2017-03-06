package com.bwei.zhanghaisheng201736;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.类的用途
 * 2.@author:zhanghaisheng
 * 3.@2017/3/6
 */


public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.MyViewHolder>{
    Context context;
    ArrayList<Bean.RsBean> list = new ArrayList<>();
    OnRVitemClickListener onRVitemClickListener;

    public Recyclerview1Adapter(Context context){

        this.context = context;

    }

    public void addList(List<Bean.RsBean> rs) {
        this.list.clear();
        this.list.addAll(rs);
        this.notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item1, parent, false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.textView.setText(list.get(position).getDirName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRVitemClickListener.onClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item1_tv);

        }
    }

    public interface OnRVitemClickListener{
        void onClickListener(int position);
    }

    public void setOnRVitemClickListener(OnRVitemClickListener onRVitemClickListener){

        this.onRVitemClickListener = onRVitemClickListener;


    }
}
