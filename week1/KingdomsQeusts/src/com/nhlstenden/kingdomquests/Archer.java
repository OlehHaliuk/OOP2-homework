package com.nhlstenden.kingdomquests;

public class Archer extends Character
{
    private boolean specialAbilityActive;

    public Archer(String name, int attackPower, int defense)
    {
        super(name, attackPower, defense);
        this.setSpecialAbilityActive(false);
    }

    public boolean isSpecialAbilityActive()
    {
        return this.specialAbilityActive;
    }

    public void setSpecialAbilityActive(boolean specialAbilityActive)
    {
        this.specialAbilityActive = specialAbilityActive;
    }

    @Override
    public void useSpecialAbility()
    {
        specialAbilityActive = true;

        System.out.println(getName()
                + " uses com.nhlstenden.kingdomquests.Archer special ability.");

        System.out.println("Next attack will deal double damage.");
    }

    @Override
    public void attack(Character enemy)
    {
        int damage = getAttackPower() - enemy.getDefense();

        if (damage < 0)
        {
            damage = 0;
        }

        if (specialAbilityActive)
        {
            damage = damage * 2;
            specialAbilityActive = false;
        }

        System.out.println(getName()
                + " attacks "
                + enemy.getName()
                + " and deals "
                + damage + " damage.");
    }
}