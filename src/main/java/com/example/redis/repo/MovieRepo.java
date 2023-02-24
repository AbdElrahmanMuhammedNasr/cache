package com.example.redis.repo;

import com.example.redis.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie,Long> {
    List<Movie> findByNameContains(String name);

    @Query("select m from Movie m ")
    Optional<Movie> findByAllv2();


}
