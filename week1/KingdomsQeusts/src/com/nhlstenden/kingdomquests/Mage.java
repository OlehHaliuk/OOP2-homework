package com.nhlstenden.kingdomquests;

public class Mage extends Character
{
    private int originalDefense;

    public Mage(String name, int attackPower, int defense)
    {
        super(name, attackPower, defense);
        this.setOriginalDefense(defense);
    }

    public int getOriginalDefense()
    {
        return this.originalDefense;
    }

    public void setOriginalDefense(int originalDefense)
    {
        this.originalDefense = originalDefense;
    }

    @Override
    public void useSpecialAbility()
    {
        setDefense(getDefense() + 20);

        System.out.println(getName()
                + " uses com.nhlstenden.kingdomquests.Mage special ability.");

        System.out.println("Defense increased to "
                + getDefense());
    }

    public void resetSpecialAbility()
    {
        setDefense(originalDefense);

        System.out.println(getName()
                + "'s defense returned to "
                + getDefense());
    }
}