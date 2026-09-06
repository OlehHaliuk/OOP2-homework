package com.nhlstenden.kingdomquests;

public abstract class Character
{
    private String name;
    private int attackPower;
    private int defense;

    public Character(String name, int attackPower, int defense)
    {
        this.setName(name);
        this.setAttackPower(attackPower);
        this.setDefense(defense);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("name cannot be null or blank");
        }

        this.name = name;
    }

    public int getAttackPower()
    {
        return this.attackPower;
    }

    public void setAttackPower(int attackPower)
    {
        this.attackPower = attackPower;
    }

    public int getDefense()
    {
        return this.defense;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }

    public void attack(Character enemy)
    {
        int damage = attackPower - enemy.getDefense();

        if (damage < 0)
        {
            damage = 0;
        }

        System.out.println(name + " attacks "
                + enemy.getName()
                + " and deals "
                + damage + " damage.");
    }

    public void defend()
    {
        System.out.println(name + " is defending.");
    }

    public abstract void useSpecialAbility();
}