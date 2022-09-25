package me.silent.testplugin.compoanents;

public class Skills {

    private int points;
    private int health;
    private int agility;
    private int intelligence;

    public Skills(int points, int health, int agility, int intelligence){
        this.points = points;
        this.health = health;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}
