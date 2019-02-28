package ru.bvpotapenko.se.j1.strings;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public boolean decreaseFood(int n) {
        if (n < 0 || n > food) {
            return false;
        } else {
            food -= n;
            return true;
        }
    }

    public void addFood(int foodAmount) {
        if (foodAmount < 0) return;
        food += foodAmount;
    }
}
