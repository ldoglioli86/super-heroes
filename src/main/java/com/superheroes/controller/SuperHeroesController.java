package com.superheroes.controller;

import com.superheroes.aspect.LogExecutionTime;
import com.superheroes.exception.HasExceptionHandlers;
import com.superheroes.model.SuperHero;
import com.superheroes.service.SuperHeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/super-heroes")
public class SuperHeroesController extends HasExceptionHandlers {

    private final SuperHeroService superHeroService;

    public SuperHeroesController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping
    @LogExecutionTime
    public List<SuperHero> getSuperHeroes() {
        return superHeroService.getAllSuperHeroes();
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<SuperHero> getSuperHeroById(@PathVariable Long id) {
        return ResponseEntity.ok(superHeroService.getSuperHeroById(id));
    }

    @GetMapping("search")
    @LogExecutionTime
    public ResponseEntity<List<SuperHero>> searchSuperHeroes(@PathParam("searchText") String searchText) {
        return ResponseEntity.ok(superHeroService.search(searchText));
    }

    @PostMapping
    @LogExecutionTime
    public void createSuperHero(@RequestBody SuperHero superHero) {
        superHeroService.createSuperHero(superHero);
    }

    @PutMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity updateSuperHero(@PathVariable Long id, @RequestBody SuperHero superHero) {
        try {
            superHeroService.updateSuperHero(id, superHero);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity deleteSuperHeroById(@PathVariable Long id) {
        try {
            superHeroService.deleteSuperHeroById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
