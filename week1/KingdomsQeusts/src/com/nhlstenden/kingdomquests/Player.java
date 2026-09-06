package com.nhlstenden.kingdomquests;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private String name;
    private Character character;
    private int xp;
    private int level;

    private List<Item> inventory;
    private List<Quest> quests;

    public Player(String name, Character character)
    {
        this.setName(name);
        this.setCharacter(character);
        this.xp = 0;
        this.level = 1;

        inventory = new ArrayList<>();
        quests = new ArrayList<>();
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

    public Character getCharacter()
    {
        return this.character;
    }

    public void setCharacter(Character character)
    {
        this.character = character;
    }

    public int getXp()
    {
        return this.xp;
    }

    public void setXp(int xp)
    {
        this.xp = xp;
    }

    public int getLevel()
    {
        return this.level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public List<Item> getInventory()
    {
        return this.inventory;
    }

    public void setInventory(List<Item> inventory)
    {
        if (inventory == null || inventory.isEmpty())
        {
            throw new IllegalArgumentException("inventory cannot be null or empty");
        }

        for (Object inventoryItem : inventory)
        {
            if (inventoryItem == null)
            {
                throw new IllegalArgumentException("inventory cannot be null");
            }
        }

        this.inventory = new ArrayList<>(inventory);
    }

    public List<Quest> getQuests()
    {
        return this.quests;
    }

    public void setQuests(List<Quest> quests)
    {
        if (quests == null || quests.isEmpty())
        {
            throw new IllegalArgumentException("quests cannot be null or empty");
        }

        for (Object questsItem : quests)
        {
            if (questsItem == null)
            {
                throw new IllegalArgumentException("quests cannot be null");
            }
        }

        this.quests = new ArrayList<>(quests);
    }

    public void addQuest(Quest quest)
    {
        quests.add(quest);
    }

    public void showQuests()
    {

        System.out.println();
        System.out.println("=== Available Quests ===");

        for (Quest quest : quests)
        {

            System.out.println(
                    quest.getTitle()
                            + " | Difficulty: "
                            + quest.getDifficulty()
                            + " | Required XP: "
                            + quest.getRequiredXP()
                            + " | Reward: "
                            + quest.getExperienceReward()
                            + " XP"
            );
        }
    }

    public void playQuest(Quest quest)
    {
        quest.play(this);
        checkLevelUp();
    }

    public void addXP(int amount)
    {
        xp += amount;

        System.out.println("Current XP: " + xp);
    }

    private void checkLevelUp()
    {

        while (xp >= 200)
        {

            xp -= 200;
            level++;

            System.out.println();
            System.out.println("LEVEL UP!");
            System.out.println("You are now level " + level);
            System.out.println("Remaining XP: " + xp);
        }
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public void showInventory()
    {

        System.out.println();
        System.out.println("=== Inventory ===");

        if (inventory.isEmpty())
        {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Item item : inventory)
        {
            System.out.println("- " + item);
        }
    }
}