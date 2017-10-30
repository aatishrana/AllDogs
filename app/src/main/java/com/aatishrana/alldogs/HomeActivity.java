package com.aatishrana.alldogs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aatishrana.alldogs.dogslist.DogsListFragment;

/**
 * Created by Aatish on 10/30/2017.
 */

public class HomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DogsListFragment fragment = new DogsListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_home_frame, fragment, "fragment_one").commit();
    }
}
