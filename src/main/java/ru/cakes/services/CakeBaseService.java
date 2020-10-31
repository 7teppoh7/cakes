package ru.cakes.services;

import org.springframework.stereotype.Service;
import ru.cakes.entities.CakeBase;
import ru.cakes.repositories.CakeBaseRepository;

@Service
public class CakeBaseService {

    private final CakeBaseRepository cakeBaseRepository;

    public CakeBaseService(CakeBaseRepository cakeBaseRepository) {
        this.cakeBaseRepository = cakeBaseRepository;
    }

    public CakeBase findByName(String name){
        return cakeBaseRepository.findByName(name);
    }

    public CakeBase save(CakeBase cakeBase) {
        return cakeBaseRepository.save(cakeBase);
    }

    public CakeBase findById(Integer id) {
        return cakeBaseRepository.findById(id).orElse(null);
    }

    public Iterable<CakeBase> findAll() {
        return cakeBaseRepository.findAll();
    }
}
