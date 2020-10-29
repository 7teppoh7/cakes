package ru.cakes.decorators;

import ru.cakes.entities.Cake;

public class CakeContainer implements CakeDecorator {

    private final Cake cake;

    public CakeContainer(Cake cake) {
        this.cake = cake;
    }

    @Override
    public void decorate() {
    }

    @Override
    public Cake getCake() {
        return cake;
    }

    @Override
    public String toString() {
        return cake.toString();
    }
}
