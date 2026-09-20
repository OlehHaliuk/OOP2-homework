package com.nhlstenden.appstore;

public class GooglePlayStore extends AppStore
{
    public GooglePlayStore(Currency currency)
    {
        super(currency);
    }

    @Override
    public double getRevenueShare()
    {
        return 0.5;
    }
}
