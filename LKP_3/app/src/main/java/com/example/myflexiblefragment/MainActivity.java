package com.example.myflexiblefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mfragmentTransction = mFragmentManager.beginTransaction();
        HomeFragment mHomeFragment = new HomeFragment();
        mfragmentTransction.add(R.id.frame_container, mHomeFragment, HomeFragment.class.getSimpleName());
        Log.d("MyflexibleFragment", "Fragment Name : " +HomeFragment.class.getSimpleName());
        mfragmentTransction.commit();
    }
}