package ru.cakes.decorators;

import lombok.NoArgsConstructor;
import ru.cakes.entities.Cake;

@NoArgsConstructor
public abstract class AbstractCakeDecorator implements CakeDecorator {

    private CakeDecorator cakeDecorator;

    public AbstractCakeDecorator(CakeDecorator cakeDecorator) {
        this.cakeDecorator = cakeDecorator;
    }

    @Override
    public Cake getCake() {
        return cakeDecorator.getCake();
    }

    @Override
    public void decorate() {
        cakeDecorator.decorate();
    }

    @Override
    public String toString() {
        return cakeDecorator.toString();
    }
}
