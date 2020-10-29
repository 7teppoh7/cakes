package ru.cakes.services;

import org.springframework.stereotype.Service;
import ru.cakes.entities.Cake;
import ru.cakes.entities.CakeBase;
import ru.cakes.repositories.CakeBaseRepository;
import ru.cakes.repositories.CakeRepository;

import java.util.Set;

@Service
public class CakeService {

    private final CakeRepository cakeRepository;
    private final CakeBaseRepository cakeBaseRepository;

    public CakeService(CakeRepository cakeRepository, CakeBaseRepository cakeBaseRepository) {
        this.cakeRepository = cakeRepository;
        this.cakeBaseRepository = cakeBaseRepository;
    }

    public Cake save(Cake cake) {
        return cakeRepository.save(cake);
    }

    public Iterable<CakeBase> findAllCakeBases() {
        return cakeBaseRepository.findAll();
    }
}
