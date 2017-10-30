package com.aatishrana.alldogs.dogslist;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aatishrana.alldogs.R;
import com.aatishrana.alldogs.model.Dog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aatish on 10/31/2017.
 */

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.ViewHolder>
{
    private List<Dog> data;

    public DogsListAdapter()
    {
        data = new ArrayList<>();
    }

    public void addDog(Dog dog)
    {
        this.data.add(dog);
        notifyItemInserted(this.data.size() - 1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dogs_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Dog dog = this.data.get(position);
        holder.textView.setText(dog.getName());

        if (dog.getPicUrl().length() > 0)
            Glide.with(holder.itemView.getContext())
                    .load(Uri.parse(dog.getPicUrl()))
                    .into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public void addAll(List<Dog> cache)
    {
        this.data.addAll(cache);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.info_img);
            textView = (TextView) itemView.findViewById(R.id.info_text);
        }
    }
}
