package com.nhlstenden.kingdomquests;

public class Warrior extends Character
{
    private int originalAttackPower;

    public Warrior(String name, int attackPower, int defense)
    {
        super(name, attackPower, defense);
        this.setOriginalAttackPower(attackPower);
    }

    public int getOriginalAttackPower()
    {
        return this.originalAttackPower;
    }

    public void setOriginalAttackPower(int originalAttackPower)
    {
        this.originalAttackPower = originalAttackPower;
    }

    @Override
    public void useSpecialAbility()
    {
        setAttackPower(getAttackPower() + 20);

        System.out.println(getName()
                + " uses com.nhlstenden.kingdomquests.Warrior special ability.");

        System.out.println("Attack power increased to "
                + getAttackPower());
    }

    public void resetSpecialAbility()
    {
        setAttackPower(originalAttackPower);

        System.out.println(getName()
                + "'s attack power returned to "
                + getAttackPower());
    }
}