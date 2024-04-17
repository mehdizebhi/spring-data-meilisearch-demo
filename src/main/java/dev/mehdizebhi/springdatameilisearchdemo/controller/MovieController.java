package dev.mehdizebhi.springdatameilisearchdemo.controller;

import dev.mehdizebhi.springdatameilisearchdemo.dto.ResponseModel;
import dev.mehdizebhi.springdatameilisearchdemo.model.Movie;
import dev.mehdizebhi.springdatameilisearchdemo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping("")
    public ResponseEntity<ResponseModel<List<Movie>>> getMovies(
            @PageableDefault Pageable pageable,
            @RequestParam("q") String query
    ) {
        var movies = movieRepository.findByQuery(query, pageable);
        return ResponseEntity
                .ok(
                        ResponseModel.builder()
                        .content(movies.getContent())
                        .pageMetadata(movies).build()
                );
    }

    @PostMapping("/sort")
    public ResponseEntity<Void> setSorts(@RequestParam("p") String[] properties) {
        movieRepository.updateSortableAttributes(properties);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
