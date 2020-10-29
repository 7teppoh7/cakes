package ru.cakes.decorators;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cakes.entities.Cake;
import ru.cakes.entities.Decoration;
import ru.cakes.services.DecorationService;

@Component
@NoArgsConstructor
public class CherryDecoration extends AbstractCakeDecorator {

    private static DecorationService decorationService;

    @Autowired
    public CherryDecoration(DecorationService decorationService) {
        CherryDecoration.decorationService = decorationService;
    }

    public CherryDecoration(CakeDecorator cakeDecorator) {
        super(cakeDecorator);
    }

    @Override
    public void decorate() {
        Cake cake = super.getCake();
        Decoration decoration = decorationService.findByName("Вишня");
        if (decoration == null) decoration = decorationService.save(new Decoration("Вишня", 50F));
        cake.getDecorations().add(decoration);
        super.decorate();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
