package com.superheroes.controller;

import com.superheroes.aspect.LogExecutionTime;
import com.superheroes.exception.HasExceptionHandlers;
import com.superheroes.model.Publisher;
import com.superheroes.service.PublisherService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController extends HasExceptionHandlers {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    @LogExecutionTime
    public List<Publisher> getPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public Publisher getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    @LogExecutionTime
    public void createPublisher(@RequestBody Publisher publisher) {
        publisherService.createPublisher(publisher);
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public void deletePublisherById(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }
}
