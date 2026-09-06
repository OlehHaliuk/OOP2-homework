package com.nhlstenden.kingdomquests;

public class Item
{
    private String title;

    public Item(String title)
    {
        this.setTitle(title);
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        if (title == null || title.isBlank())
        {
            throw new IllegalArgumentException("title cannot be null or blank");
        }

        this.title = title;
    }

    @Override
    public String toString()
    {
        return title;
    }
}