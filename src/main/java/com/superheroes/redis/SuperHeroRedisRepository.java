package com.superheroes.redis;

import com.superheroes.model.SuperHero;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class SuperHeroRedisRepository implements IRedisRepository{
    private static final String KEY = "SuperHero";

    private RedisTemplate<String, SuperHero> redisTemplate;
    private HashOperations hashOperations;

    public SuperHeroRedisRepository(RedisTemplate<String, SuperHero> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public List<SuperHero> findAll() {
        return hashOperations.values(KEY);
    }

    @Override
    public SuperHero findById(String id) {
        return (SuperHero) hashOperations.get(KEY, id);
    }

    @Override
    public void save(SuperHero superhero) {
        hashOperations.put(KEY, String.valueOf(superhero.getId()), superhero);
    }

    @Override
    public void update(String id, SuperHero superhero) {
        delete(id);
        save(superhero);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }
}
