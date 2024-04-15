package dev.mehdizebhi.springdatameilisearchdemo.controller;

import dev.mehdizebhi.springdatameilisearchdemo.model.Movie;
import dev.mehdizebhi.springdatameilisearchdemo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping("")
    public Page<Movie> getMovies(
            @PageableDefault Pageable pageable,
            @RequestParam("q") String query
    ) {
        return movieRepository.findByQuery(query, pageable);
    }

    @PostMapping("/sort")
    public ResponseEntity<String> setSorts(@RequestParam("p") String[] properties) {
        movieRepository.updateSortableAttributes(properties);
        return ResponseEntity.ok("Added");
    }
}
