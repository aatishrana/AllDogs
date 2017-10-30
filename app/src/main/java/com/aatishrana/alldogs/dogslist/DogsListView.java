package com.aatishrana.alldogs.dogslist;

import com.aatishrana.alldogs.model.Dog;

import java.util.List;

/**
 * Created by Aatish on 10/30/2017.
 */
public interface DogsListView
{
    void loadPic(String url);

    void showDogs(List<Dog> cache);
}
