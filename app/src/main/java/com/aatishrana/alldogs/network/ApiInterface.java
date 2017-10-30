package com.aatishrana.alldogs.network;

import com.aatishrana.alldogs.model.AllBreed;
import com.aatishrana.alldogs.model.AllImages;
import com.aatishrana.alldogs.model.AllSubBreed;
import com.aatishrana.alldogs.model.OneRandom;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Aatish on 10/29/2017.
 */

public interface ApiInterface
{
    @GET("api/breeds/list")
    Observable<AllBreed> getAllBreeds();

    @GET("api/breeds/image/random")
    Observable<OneRandom> getARandomPic();

    @GET("api/breed/{dog_name}/images")
    Observable<AllImages> getImagesOfADog(@Path(value = "dog_name", encoded = true) String dog_name);

    @GET("api/breed/{dog_name}/images/random")
    Observable<OneRandom> getARandomPicOfADog(@Path(value = "dog_name", encoded = true) String dog_name);

    @GET("api/breed/{dog_name}/list")
    Observable<AllSubBreed> getSubBreedOfADog(@Path(value = "dog_name", encoded = true) String dog_name);
}
