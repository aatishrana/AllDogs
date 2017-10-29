package com.aatishrana.alldogs.network;

import com.aatishrana.alldogs.model.AllBreed;
import com.aatishrana.alldogs.model.AllImages;
import com.aatishrana.alldogs.model.AllSubBreed;
import com.aatishrana.alldogs.model.OneRandom;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Aatish on 10/29/2017.
 */

public interface ApiInterface
{
    @GET("api/breeds/list")
    Call<AllBreed> getAllBreeds();

    @GET("api/breeds/image/random")
    Call<OneRandom> getARandomPic();

    @GET("api/breed/{dog_name}/images")
    Call<AllImages> getImagesOfADog(@Path(value = "dog_name", encoded = true) String dog_name);

    @GET("api/breed/{dog_name}/images/random")
    Call<OneRandom> getARandomPicOfADog(@Path(value = "dog_name", encoded = true) String dog_name);

    @GET("api/breed/{dog_name}/list")
    Call<AllSubBreed> getSubBreedOfADog(@Path(value = "dog_name", encoded = true) String dog_name);
}
