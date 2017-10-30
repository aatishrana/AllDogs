package com.aatishrana.alldogs.base;

/**
 * Created by Aatish on 10/30/2017.
 */

public interface Presenter<V>
{
    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}
