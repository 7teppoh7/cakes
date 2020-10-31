package ru.cakes.decorators;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cakes.entities.Cake;
import ru.cakes.entities.CakeBase;
import ru.cakes.services.CakeBaseService;

@Component
@NoArgsConstructor
public class KorzhCakeBase extends AbstractCakeDecorator {

    private static CakeBaseService cakeBaseService;

    public KorzhCakeBase(CakeDecorator cakeDecorator) {
        super(cakeDecorator);
    }

    @Autowired
    public KorzhCakeBase(CakeBaseService cakeBaseService) {
        KorzhCakeBase.cakeBaseService = cakeBaseService;
    }

    @Override
    public void decorate() {
        Cake cake = super.getCake();
        CakeBase cakeBase = cakeBaseService.findByName("Корж");
        if (cakeBase == null) cakeBase = cakeBaseService.save(new CakeBase("Корж"));
        cake.setCakeBase(cakeBase);
        super.decorate();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
