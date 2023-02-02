package com.superheroes.repository;

import com.superheroes.model.SuperHero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {
}
