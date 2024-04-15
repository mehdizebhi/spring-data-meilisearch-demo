package dev.mehdizebhi.springdatameilisearchdemo.repository;

import dev.mehdizebhi.springdatameilisearchdemo.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieRepository {

    Page<Movie> findByQuery(String query, Pageable pageable);

    void updateSortableAttributes(String[] attributes);

}
