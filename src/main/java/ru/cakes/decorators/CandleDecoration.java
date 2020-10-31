package ru.cakes.decorators;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cakes.entities.Cake;
import ru.cakes.entities.Decoration;
import ru.cakes.services.DecorationService;

@Component
@NoArgsConstructor
public class CandleDecoration extends AbstractCakeDecorator {

    private static DecorationService decorationService;

    @Autowired
    public CandleDecoration(DecorationService decorationService) {
        CandleDecoration.decorationService = decorationService;
    }

    public CandleDecoration(CakeDecorator cakeDecorator) {
        super(cakeDecorator);
    }

    @Override
    public void decorate() {
        Cake cake = super.getCake();
        Decoration decoration = decorationService.findByName("Свечи");
        if (decoration == null) decoration = decorationService.save(new Decoration("Свечи", 25F));
        cake.getDecorations().add(decoration);
        super.decorate();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
