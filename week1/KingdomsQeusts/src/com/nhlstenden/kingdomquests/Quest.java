package com.nhlstenden.kingdomquests;

public class Quest
{
    private String title;
    private int experienceReward;
    private int difficulty;
    private Character enemy;

    public Quest(String title, int experienceReward, int difficulty, Character enemy)
    {
        this.setTitle(title);
        this.setExperienceReward(experienceReward);
        this.setDifficulty(difficulty);
        this.setEnemy(enemy);
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        if (title == null || title.isBlank())
        {
            throw new IllegalArgumentException("title cannot be null or blank");
        }

        this.title = title;
    }

    public int getExperienceReward()
    {
        return this.experienceReward;
    }

    public void setExperienceReward(int experienceReward)
    {
        this.experienceReward = experienceReward;
    }

    public int getDifficulty()
    {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public Character getEnemy()
    {
        return this.enemy;
    }

    public void setEnemy(Character enemy)
    {
        this.enemy = enemy;
    }

    public int getRequiredXP()
    {
        return difficulty * 10;
    }

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
        System.out.println("com.nhlstenden.kingdomquests.Quest started: " + title);
        System.out.println("Enemy: " + enemy.getName());

        player.getCharacter().attack(enemy);

        System.out.println("com.nhlstenden.kingdomquests.Quest completed!");

        player.addXP(experienceReward);

        System.out.println("You received "
                + experienceReward + " XP.");
    }
}