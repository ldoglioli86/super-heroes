package com.superheroes.repository;

import com.superheroes.model.SuperHero;
import org.springframework.data.repository.CrudRepository;

public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {
}
