package com.superheroes.api.redis;

import com.superheroes.model.Publisher;
import com.superheroes.model.SuperHero;
import com.superheroes.model.enums.Alignment;
import com.superheroes.model.enums.Gender;
import com.superheroes.model.enums.Race;
import com.superheroes.redis.SuperHeroRedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SuperHeroRedisRepositoryTests {
    @Autowired
    SuperHeroRedisRepository superHeroRedisRepository;

    @BeforeEach
    void setUp() {
        var allSuperHeroes = superHeroRedisRepository.findAll();
        for (var superHero : allSuperHeroes ) {
            superHeroRedisRepository.delete(String.valueOf(superHero.getId()));
        }
    }

    @Test
    public void saveSuperHeroShouldSaveIt() {
        // given
        var allSuperHeroes = superHeroRedisRepository.findAll();
        assertThat(allSuperHeroes).hasSize(0);

        var superHeroId = 1L;
        var superHeroName = "Iron Man";

        // when
        superHeroRedisRepository.save(createSuperHero(superHeroId, superHeroName));

        // then
        allSuperHeroes = superHeroRedisRepository.findAll();
        assertThat(allSuperHeroes).hasSize(1);
        assertThat(allSuperHeroes.get(0).getId()).isEqualTo(superHeroId);
        assertThat(allSuperHeroes.get(0).getName()).isEqualTo(superHeroName);
    }

    @Test
    public void findByIdShouldRetrieveASuperHero() {
        // given
        var superHeroId = 1L;
        var superHeroName = "Iron Man";
        superHeroRedisRepository.save(createSuperHero(superHeroId, superHeroName));

        // when
        var superHero = superHeroRedisRepository.findById(String.valueOf(superHeroId));

        // then
        assertThat(superHero.getId()).isEqualTo(superHeroId);
        assertThat(superHero.getName()).isEqualTo(superHeroName);
    }

    private SuperHero createSuperHero(Long id, String Name) {
        var superHero = new SuperHero();
        superHero.setId(1L);
        superHero.setName("Iron Man");
        superHero.setGender(Gender.MALE);
        superHero.setAlignment(Alignment.GOOD);
        superHero.setRace(Race.HUMAN);
        superHero.setPublisher(createPublisher(1L, "Marvel"));
        return superHero;
    }

    private Publisher createPublisher(Long id, String name) {
        var publisher = new Publisher();
        publisher.setId(id);
        publisher.setName(name);
        return publisher;
    }
}
