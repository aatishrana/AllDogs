package com.aatishrana.alldogs.dogslist;

import com.aatishrana.alldogs.DataRepository;
import com.aatishrana.alldogs.base.PresenterFactory;

/**
 * Created by Aatish on 10/30/2017.
 */

public class DogsListPresenterFactory implements PresenterFactory<DogsListPresenter>
{
    private DataRepository dataRepository;

    public DogsListPresenterFactory(DataRepository dataRepository)
    {
        this.dataRepository = dataRepository;
    }

    @Override
    public DogsListPresenter create()
    {
        return new DogsListPresenter(this.dataRepository);
    }
}
