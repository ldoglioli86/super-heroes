package com.superheroes.controller;

import com.superheroes.aspect.LogExecutionTime;
import com.superheroes.model.SuperHero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/super-heroes")
public class SuperHeroesController {

    @GetMapping
    @LogExecutionTime
    public List<SuperHero> getSuperHeroes() {
        return new ArrayList<>();
    }
}
