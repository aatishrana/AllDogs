package com.aatishrana.alldogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aatishrana.alldogs.model.AllBreed;
import com.aatishrana.alldogs.model.AllImages;
import com.aatishrana.alldogs.model.AllSubBreed;
import com.aatishrana.alldogs.model.OneRandom;
import com.aatishrana.alldogs.network.ApiClient;
import com.aatishrana.alldogs.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{


    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        findViewById(R.id.activity_main_all).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                apiInterface.getAllBreeds().enqueue(new Callback<AllBreed>()
                {
                    @Override
                    public void onResponse(Call<AllBreed> call, Response<AllBreed> response)
                    {
                        if (response.isSuccessful())
                            Log.e("dog", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<AllBreed> call, Throwable t)
                    {
                        t.printStackTrace();
                    }
                });
            }
        });


        findViewById(R.id.activity_main_a_random).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                apiInterface.getARandomPic().enqueue(new Callback<OneRandom>()
                {
                    @Override
                    public void onResponse(Call<OneRandom> call, Response<OneRandom> response)
                    {
                        if (response.isSuccessful())
                            Log.e("dog", "" + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<OneRandom> call, Throwable t)
                    {
                        t.printStackTrace();
                    }
                });
            }
        });


        findViewById(R.id.activity_main_img_of_one).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                apiInterface.getImagesOfADog("hound").enqueue(new Callback<AllImages>()
                {
                    @Override
                    public void onResponse(Call<AllImages> call, Response<AllImages> response)
                    {
                        if (response.isSuccessful())
                            Log.e("dog", "" + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<AllImages> call, Throwable t)
                    {
                        t.printStackTrace();
                    }
                });
            }
        });


        findViewById(R.id.activity_main_a_random_img_of_one).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                apiInterface.getARandomPicOfADog("hound").enqueue(new Callback<OneRandom>()
                {
                    @Override
                    public void onResponse(Call<OneRandom> call, Response<OneRandom> response)
                    {
                        if (response.isSuccessful())
                            Log.e("dog", "" + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<OneRandom> call, Throwable t)
                    {
                        t.printStackTrace();
                    }
                });
            }
        });


        findViewById(R.id.activity_main_sub_breed_of_one).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                apiInterface.getSubBreedOfADog("hound").enqueue(new Callback<AllSubBreed>()
                {
                    @Override
                    public void onResponse(Call<AllSubBreed> call, Response<AllSubBreed> response)
                    {
                        if (response.isSuccessful())
                            Log.e("dog", "" + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<AllSubBreed> call, Throwable t)
                    {
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}
