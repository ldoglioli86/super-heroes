package com.superheroes.service;

import com.superheroes.model.SuperHero;
import com.superheroes.redis.SuperHeroRedisRepository;
import com.superheroes.repository.SuperHeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SuperHeroService {

    private final SuperHeroRepository superHeroRepository;
    private final SuperHeroRedisRepository superHeroRedisRepository;

    public SuperHeroService(SuperHeroRepository superHeroRepository, SuperHeroRedisRepository superHeroRedisRepository) {
        this.superHeroRepository = superHeroRepository;
        this.superHeroRedisRepository = superHeroRedisRepository;
    }

    public List<SuperHero> getAllSuperHeroes() {
        var valuesFromCache = superHeroRedisRepository.findAll();
        if (valuesFromCache.isEmpty()) {
            var valuesFromDb = superHeroRepository.findAll();
            superHeroRedisRepository.refresh(valuesFromDb);
            return StreamSupport.stream(valuesFromDb.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return valuesFromCache;
    }

    public SuperHero getSuperHeroById(Long id) {
        var valueFromCache = superHeroRedisRepository.findById(String.valueOf(id));
        if (valueFromCache == null) {
            var valueFromDb = superHeroRepository.findById(id);
            if (valueFromDb.isEmpty()) {
                throw new NoSuchElementException("Super Hero does not exists in the database");
            }
            var valuesFromDb = superHeroRepository.findAll();
            superHeroRedisRepository.refresh(valuesFromDb);
            return valueFromDb.get();
        }
        return valueFromCache;
    }

    public void createSuperHero(SuperHero superHero) {
        var savedItem = superHeroRepository.save(superHero);
        superHeroRedisRepository.save(savedItem);
    }

    public void updateSuperHero(Long id, SuperHero superHero) {
        var updatedItem =superHeroRepository.save(superHero);
        superHeroRedisRepository.update(String.valueOf(id), updatedItem);
    }

    public void deleteSuperHeroById(Long id) {
        superHeroRepository.deleteById(id);
        superHeroRedisRepository.delete(String.valueOf(id));
    }

    public List<SuperHero> search(String searchText) {
        if (searchText.length() < 4) {
            throw new IllegalArgumentException("Search parameter length should be longer than 3 characters.");
        }
        var allItems = superHeroRepository.findAll();
        var allItemsList = StreamSupport.stream(allItems.spliterator(), false)
                .collect(Collectors.toList());
        return allItemsList
                .stream()
                .filter(superHero -> superHero.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }
}
