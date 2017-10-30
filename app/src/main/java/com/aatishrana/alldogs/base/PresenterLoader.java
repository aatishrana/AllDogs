package com.aatishrana.alldogs.base;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by Aatish on 10/30/2017.
 */

public final class PresenterLoader<T extends Presenter> extends Loader<T>
{

    private final PresenterFactory<T> factory;
    private T presenter;

    PresenterLoader(Context context, PresenterFactory<T> factory)
    {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading()
    {

        // if we already own a presenter instance, simply deliver it.
        if (presenter != null)
        {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad()
    {

        // Create the Presenter using the Factory
        presenter = factory.create();

        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    protected void onReset()
    {
        if (presenter != null)
        {
            presenter.onDestroyed();
            presenter = null;
        }
    }

    public T getPresenter()
    {
        return presenter;
    }
}

