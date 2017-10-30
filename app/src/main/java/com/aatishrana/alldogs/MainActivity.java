package com.aatishrana.alldogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aatishrana.alldogs.model.Dog;
import com.aatishrana.alldogs.network.ApiClient;
import com.aatishrana.alldogs.network.ApiInterface;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity
{

    private ApiInterface apiInterface;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        dataRepository = new DataRepositoryImpl(apiInterface);

        findViewById(R.id.activity_main_all).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dataRepository.getDogs()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Dog>()
                        {
                            @Override
                            public void accept(Dog dog) throws Exception
                            {
                                if (dog != null)
                                    Help.L(dog.toString());
                            }
                        });
            }
        });


        findViewById(R.id.activity_main_a_random).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dataRepository.getARandomPic()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>()
                        {
                            @Override
                            public void accept(String url) throws Exception
                            {
                                Help.L(url);
                            }
                        });
            }
        });


        findViewById(R.id.activity_main_img_of_one).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dataRepository.getAllImages("hound")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<String>>()
                        {
                            @Override
                            public void accept(List<String> images) throws Exception
                            {
                                Help.L(images.toString());
                            }
                        });
            }
        });


//        findViewById(R.id.activity_main_sub_breed_of_one).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                apiInterface.getSubBreedOfADog("hound")
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<AllSubBreed>()
//                        {
//                            @Override
//                            public void accept(AllSubBreed allSubBreed) throws Exception
//                            {
//                                if (allSubBreed != null)
//                                    Help.L(allSubBreed.toString());
//                            }
//                        });
//            }
//        });
    }
}
