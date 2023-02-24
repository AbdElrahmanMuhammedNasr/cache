package com.example.redis.service;

import com.example.redis.domain.Movie;
import com.example.redis.repo.MovieRepo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {
    private MovieRepo movieRepo;

    private static final String CACHE_NAME="GET_";


    @CacheEvict(value="Movie",  allEntries = true)
    public Movie saveMovie(Movie movie){
        return movieRepo.save(movie);
    }

    @CacheEvict(value="Movie",  allEntries = true)
    public void deleteMovie(Long movieId){
         movieRepo.deleteById(movieId);
    }
//CACHE_NAME.concat("ALL")
    @Cacheable(value="Movie", key="{'"+CACHE_NAME+"ALL'}")
    public List<Movie> findAllMovie() {
        return movieRepo.findAll()
                .stream()
                .map(this::changeEvenIdToEven)
                .collect(Collectors.toList());
    }

    @Cacheable(value="Movie", key="{'"+CACHE_NAME+"',#name}",unless = "#result.size()<100")
    public List<Movie> findByName(String name) {
        return movieRepo.findByNameContains(name);
    }

    private Movie changeEvenIdToEven(Movie movie){
        if(movie.getId() % 2 ==0 ) {
            movie.setName("Even");
            return  movie;
        }
        return movie;
    }


}
