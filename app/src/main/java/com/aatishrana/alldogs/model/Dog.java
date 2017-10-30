package com.aatishrana.alldogs.model;

/**
 * Created by Aatish on 10/30/2017.
 */

public class Dog
{
    private final String name, picUrl;

    public Dog(String name, String picUrl)
    {
        this.name = name;
        this.picUrl = picUrl;
    }

    public String getName()
    {
        return name;
    }

    public String getPicUrl()
    {
        return picUrl;
    }

    @Override
    public String toString()
    {
        return "Dog{" +
                "name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
