package com.nhlstenden.appstore;

public class DownloadNotAllowedException extends Exception
{
    public DownloadNotAllowedException(String message)
    {
        super(message);
    }
}
