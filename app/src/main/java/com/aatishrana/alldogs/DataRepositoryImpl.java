package com.aatishrana.alldogs;

import com.aatishrana.alldogs.model.AllBreed;
import com.aatishrana.alldogs.model.AllImages;
import com.aatishrana.alldogs.model.Dog;
import com.aatishrana.alldogs.model.OneRandom;
import com.aatishrana.alldogs.network.ApiInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Aatish on 10/30/2017.
 */

public class DataRepositoryImpl implements DataRepository
{

    private ApiInterface apiInterface;

    public DataRepositoryImpl(ApiInterface apiInterface)
    {
        this.apiInterface = apiInterface;
    }

    @Override
    public Observable<Dog> getDogs()
    {
        return apiInterface.getAllBreeds()
                .map(new Function<AllBreed, List<String>>()
                {
                    @Override
                    public List<String> apply(@NonNull AllBreed allBreed) throws Exception
                    {
                        if (allBreed != null)
                            return allBreed.getMessage();
                        else
                            return null;
                    }
                })
                .flatMap(new Function<List<String>, Observable<String>>()
                {
                    @Override
                    public Observable<String> apply(@NonNull List<String> strings) throws Exception
                    {
                        return Observable.fromIterable(strings);
                    }
                })
                .flatMap(new Function<String, Observable<Dog>>()
                {
                    @Override
                    public Observable<Dog> apply(@NonNull final String dogName) throws Exception
                    {
                        return apiInterface.getARandomPicOfADog(dogName)
                                .map(new Function<OneRandom, Dog>()
                                {
                                    @Override
                                    public Dog apply(@NonNull OneRandom oneRandom) throws Exception
                                    {
                                        return new Dog(dogName, oneRandom.getMessage());
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<String> getARandomPic()
    {
        return apiInterface.getARandomPic()
                .map(new Function<OneRandom, String>()
                {
                    @Override
                    public String apply(@NonNull OneRandom oneRandom) throws Exception
                    {
                        if (oneRandom != null)
                            return oneRandom.getMessage();
                        else
                            return "";
                        //todo fix one dog image as default
                    }
                });
    }

    @Override
    public Observable<List<String>> getAllImages(String dogName)
    {
        return apiInterface.getImagesOfADog("hound")
                .map(new Function<AllImages, List<String>>()
                {
                    @Override
                    public List<String> apply(@NonNull AllImages allImages) throws Exception
                    {
                        if (allImages != null)
                            return allImages.getMessage();
                        else
                            return null;
                    }
                });
    }
}
