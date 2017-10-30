package com.aatishrana.alldogs.dogslist;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.aatishrana.alldogs.DataRepository;
import com.aatishrana.alldogs.DataRepositoryImpl;
import com.aatishrana.alldogs.R;
import com.aatishrana.alldogs.base.BasePresenterFragment;
import com.aatishrana.alldogs.base.PresenterFactory;
import com.aatishrana.alldogs.model.Dog;
import com.aatishrana.alldogs.network.ApiClient;
import com.aatishrana.alldogs.network.ApiInterface;
import com.bumptech.glide.Glide;


import java.util.List;

import static com.aatishrana.alldogs.Help.ID_DOGS_LIST;

/**
 * Created by Aatish on 10/30/2017.
 */

public class DogsListFragment extends BasePresenterFragment<DogsListPresenter, DogsListView> implements DogsListView
{
    private DogsListPresenter presenter;

    private DogsListPresenterFactory presenterFactory;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private DogsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_dogs_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        boilerPlate();
        Button button = (Button) view.findViewById(R.id.fragment_dogs_list_btn_new_pic);
        imageView = (ImageView) view.findViewById(R.id.toolbarImage);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_dogs_list_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new DogsListAdapter();
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.getRandomPic();
            }
        });
    }


    @Override
    public void onResume()
    {
        super.onResume();
        presenter.getAllDogs();
    }

    private void boilerPlate()
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        DataRepository dataRepository = new DataRepositoryImpl(apiInterface);
        presenterFactory = new DogsListPresenterFactory(dataRepository);
    }

    @NonNull
    @Override
    protected PresenterFactory<DogsListPresenter> getPresenterFactory()
    {
        return presenterFactory;
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull DogsListPresenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    protected int loaderId()
    {
        return ID_DOGS_LIST;
    }

    @Override
    public void loadPic(String url)
    {
        Glide.with(getActivity())
                .load(Uri.parse(url))
                .thumbnail(0.1f)
                .into(imageView);
    }

    @Override
    public void showDogs(List<Dog> cache)
    {
        if(adapter!=null)
            adapter.addAll(cache);
    }
}
