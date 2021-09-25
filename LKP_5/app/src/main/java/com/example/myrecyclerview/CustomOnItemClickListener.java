package com.example.myrecyclerview;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;
    CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallbak){
        this.position=position;
        this.onItemClickCallback=onItemClickCallbak;
    }
    @Override
    public void onClick(View view){
        onItemClickCallback.onItemClicked(view,position);
    }
    public interface OnItemClickCallback{
        void onItemClicked(View view, int position);
    }
}
