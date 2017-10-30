package com.aatishrana.alldogs.dogslist;

import com.aatishrana.alldogs.DataRepository;
import com.aatishrana.alldogs.base.Presenter;
import com.aatishrana.alldogs.model.Dog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Aatish on 10/30/2017.
 */
public class DogsListPresenter implements Presenter<DogsListView>
{
    private DataRepository dataRepository;
    private DogsListView view;

    private List<Dog> cache;

    public DogsListPresenter(DataRepository dataRepository)
    {
        this.dataRepository = dataRepository;
        this.cache = new ArrayList<>();
    }

    @Override
    public void onViewAttached(DogsListView view)
    {
        this.view = view;
    }

    @Override
    public void onViewDetached()
    {
        this.view = null;
    }

    @Override
    public void onDestroyed()
    {

    }

    public void getRandomPic()
    {
        dataRepository.getARandomPic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>()
                {
                    @Override
                    public void onSubscribe(@NonNull Disposable d)
                    {

                    }

                    @Override
                    public void onNext(@NonNull String url)
                    {
                        view.loadPic(url);
                    }

                    @Override
                    public void onError(@NonNull Throwable e)
                    {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
    }

    public void getAllDogs()
    {
        if (this.cache != null && !this.cache.isEmpty())
            view.showDogs(cache);
        else
            this.dataRepository.getDogs()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Dog>()
                    {
                        @Override
                        public void onSubscribe(@NonNull Disposable d)
                        {

                        }

                        @Override
                        public void onNext(@NonNull Dog dog)
                        {
                            if (dog != null)
                                cache.add(dog);
                        }

                        @Override
                        public void onError(@NonNull Throwable e)
                        {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete()
                        {
                            view.showDogs(cache);
                        }
                    });
    }
}
