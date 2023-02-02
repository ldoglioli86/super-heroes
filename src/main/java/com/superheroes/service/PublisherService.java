package com.superheroes.service;

import com.superheroes.model.Publisher;
import com.superheroes.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        return StreamSupport.stream(publisherRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).get();
    }

    public void createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
