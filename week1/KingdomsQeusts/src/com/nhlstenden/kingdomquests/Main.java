package com.nhlstenden.kingdomquests;

public class Main
{
    public static void main(String[] args)
    {

        Warrior warrior = new Warrior(
                "Arthas",
                50,
                30
        );

        Mage mage = new Mage(
                "Merlin",
                40,
                40
        );

        Archer archer = new Archer(
                "Legolas",
                45,
                25
        );

        Player player = new Player(
                "Player",
                warrior
        );

        Quest goblinQuest = new Quest(
                "Defeat the Goblin",
                100,
                5,
                mage
        );

        Quest dragonQuest = new Quest(
                "Defeat the Dragon",
                150,
                10,
                mage
        );

        Item magicSword = new Item("Magic Sword");

        SpecialQuest treasureQuest = new SpecialQuest(
                "Find the Lost Treasure",
                100,
                5,
                archer,
                magicSword
        );

        player.addQuest(goblinQuest);
        player.addQuest(dragonQuest);
        player.addQuest(treasureQuest);

        player.showQuests();

        System.out.println();

        warrior.useSpecialAbility();

        player.playQuest(goblinQuest);

        System.out.println();
        System.out.println("com.nhlstenden.kingdomquests.Player XP: " + player.getXp());
        System.out.println("com.nhlstenden.kingdomquests.Player level: " + player.getLevel());

        player.playQuest(treasureQuest);

        player.showInventory();

        System.out.println();
        System.out.println("=== com.nhlstenden.kingdomquests.Player Status ===");
        System.out.println("Name: " + player.getName());
        System.out.println("com.nhlstenden.kingdomquests.Character: "
                + player.getCharacter().getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getXp());
    }
}