package com.aatishrana.alldogs;

import com.aatishrana.alldogs.model.Dog;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Aatish on 10/30/2017.
 */

public interface DataRepository
{
    Observable<Dog> getDogs();

    Observable<String> getARandomPic();

    Observable<List<String>> getAllImages(String dogName);
}
