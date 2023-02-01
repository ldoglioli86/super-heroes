package com.superheroes.redis;

import com.superheroes.model.SuperHero;

import java.util.List;

public interface IRedisRepository {
    List<SuperHero> findAll();
    SuperHero findById(String id);
    void save(SuperHero student);
    void update(String id, SuperHero student);
    void delete(String id);
}
