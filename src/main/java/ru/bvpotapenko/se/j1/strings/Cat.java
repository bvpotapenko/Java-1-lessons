package ru.bvpotapenko.se.j1.strings;

public class Cat {
    private String name;
    private int appetite;
    private boolean bellyful;
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.bellyful = false;
    }
    public void eat(Plate p) {
        if(bellyful) return;
        bellyful = p.decreaseFood(appetite);
    }
    public boolean isBellyful() {
        return bellyful;
    }

    public String getName() {
        return name;
    }
}
