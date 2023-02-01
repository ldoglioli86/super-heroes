package com.superheroes.api.repository;

import com.superheroes.model.Publisher;
import com.superheroes.model.SuperHero;
import com.superheroes.model.enums.Alignment;
import com.superheroes.model.enums.Gender;
import com.superheroes.model.enums.Race;
import com.superheroes.repository.PublisherRepository;
import com.superheroes.repository.SuperHeroRepository;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SuperHeroRepositoryTests {

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    SuperHeroRepository superHeroRepository;

    private SuperHero superHeroIronman;
    private SuperHero superHeroBatman;

    @BeforeEach
    public void setUp() {
        superHeroRepository.deleteAll();
        publisherRepository.deleteAll();

        publisherRepository.save(createPublisher("Marvel"));
        var publishers = publisherRepository.findAll();
        var marvelPublisher = StreamSupport.stream(publishers.spliterator(), false)
                        .filter(p -> "Marvel".equals(p.getName())).findFirst();

        superHeroIronman = superHeroRepository.save(createSuperHero("Iron Man", marvelPublisher.get()));
        superHeroBatman = superHeroRepository.save(createSuperHero("Batman", marvelPublisher.get()));
    }

    @Test
    public void findAll_shouldRetrieve_ExistingItems() {
        var allSuperHeroes = superHeroRepository.findAll();
        assertThat(allSuperHeroes).hasSize(2);
        assertThat(allSuperHeroes).extracting(s -> s.getId())
            .containsExactly(superHeroIronman.getId(), superHeroBatman.getId());
    }

    @Test
    public void findById_shouldRetrieveOneItem() {
        var ironManFromDb = superHeroRepository.findById(superHeroIronman.getId());
        assertThat(ironManFromDb.get()).isEqualTo(superHeroIronman);
    }

    private SuperHero createSuperHero(String name, Publisher publisher) {
        var superHero = new SuperHero();
        superHero.setName(name);
        superHero.setGender(Gender.MALE);
        superHero.setAlignment(Alignment.GOOD);
        superHero.setRace(Race.HUMAN);
        superHero.setPublisher(publisher);
        return superHero;
    }

    private Publisher createPublisher(String name) {
        var publisher = new Publisher();
        publisher.setName(name);
        return publisher;
    }
}
