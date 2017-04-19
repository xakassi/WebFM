package ru.javastudy.springMVC.extra;

public class Pair<E1, E2> {
    private E1 x;
    private E2 y;

    public Pair(E1 x, E2 y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
