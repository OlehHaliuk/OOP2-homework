package com.nhlstenden.uservalidation;

import java.util.ArrayList;

public class UserStorage
{
    private final ArrayList<User> users;

    public UserStorage()
    {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers()
    {
        return this.users;
    }

    public int getUserCount()
    {
        return this.users.size();
    }

    public void saveUser(User user)
    {
        this.users.add(user);
    }

    public boolean usernameExists(String name)
    {
        for (User user : this.users)
        {
            if (user.getName().equals(name))
            {
                return true;
            }
        }

        return false;
    }
}
