package ru.cakes.decorators;

import ru.cakes.entities.Cake;

public class NameDecorator extends AbstractCakeDecorator {

    private final String name;

    public NameDecorator(CakeDecorator cakeDecorator, String name) {
        super(cakeDecorator);
        this.name = name;
    }

    @Override
    public void decorate() {
        Cake cake = super.getCake();
        cake.setName(name);
        super.decorate();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
