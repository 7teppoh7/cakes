package ru.cakes.decorators;

import ru.cakes.entities.Cake;

public interface CakeDecorator {

    void decorate();

    Cake getCake();
}
