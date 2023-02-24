package com.example.redis.rest;

import com.example.redis.domain.Movie;
import com.example.redis.repo.MovieRepo;
import com.example.redis.service.MovieService;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class MovieResourse {

    private final MovieService movieService;
    private final MovieRepo movieRepo;


    @GetMapping(value = "/add")
    public ResponseEntity<Movie> add(){
        Movie movie = new Movie("abdo", "CS", 12);
        return ResponseEntity.ok().body(movieService.saveMovie(movie));
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Movie>> get(){
        return ResponseEntity.ok().body(movieService.findAllMovie());
    }

    @GetMapping(value = "/get-name/{name}")
    public ResponseEntity<List<Movie>> getName(@PathVariable("name") String name){
        return ResponseEntity.ok().body(movieService.findByName(name));
    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().body("delete");
    }


}
