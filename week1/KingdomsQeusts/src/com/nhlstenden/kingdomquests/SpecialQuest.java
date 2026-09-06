package com.nhlstenden.kingdomquests;

public class SpecialQuest extends Quest
{
    private Item item;

    public SpecialQuest(String title, int experienceReward, int difficulty, Character enemy, Item item)
    {
        super(title, experienceReward, difficulty, enemy);
        this.setItem(item);
    }

    public Item getItem()
    {
        return this.item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    @Override
    public void play(Player player)
    {

        if (player.getXp() < getRequiredXP())
        {
            System.out.println("You do not have enough XP.");
            System.out.println("Required XP: " + getRequiredXP());
            System.out.println("Your XP: " + player.getXp());
            return;
        }

        System.out.println();
        System.out.println("Special quest started: " + getTitle());

        System.out.println("Enemy: "
                + getEnemy().getName());

        player.getCharacter().attack(getEnemy());

        System.out.println("Special quest completed!");

        player.addXP(getExperienceReward());
        player.addItem(item);

        System.out.println("You received "
                + getExperienceReward() + " XP.");

        System.out.println("You received item: "
                + item.getTitle());
    }
}