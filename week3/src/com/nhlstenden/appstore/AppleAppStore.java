package com.nhlstenden.appstore;

public class AppleAppStore extends AppStore
{
    public AppleAppStore(Currency currency)
    {
        super(currency);
    }

    @Override
    public void addApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("App cannot be null");
        }
        if (app.isNudity())
        {
            throw new IllegalArgumentException("Apple store does not accept apps with nudity");
        }
        super.addApp(app);
    }

    @Override
    public double getRevenueShare()
    {
        return 0.7;
    }
}
