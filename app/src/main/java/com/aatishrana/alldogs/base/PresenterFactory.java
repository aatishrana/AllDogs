package com.aatishrana.alldogs.base;

/**
 * Created by Aatish on 10/30/2017.
 */

public interface PresenterFactory<T extends Presenter>
{
    T create();
}
