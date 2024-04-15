package dev.mehdizebhi.springdatameilisearchdemo.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.SearchResultPaginated;
import dev.mehdizebhi.springdatameilisearchdemo.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MovieRepositoryImpl implements MovieRepository {

    private final Client client;

    @Override
    public Page<Movie> findByQuery(String query, Pageable pageable) {
        Index movieIndex = client.index("movies");

        var sortList = pageable.getSort().get()
                .map(order -> order.getProperty() + ":" + order.getDirection().name().toLowerCase())
                .collect(Collectors.toList());
        String[] sorts = new String[sortList.size()];
        sortList.toArray(sorts);

        SearchRequest request = SearchRequest.builder()
                .q(query)
                .hitsPerPage(pageable.getPageSize())
                .page(pageable.getPageNumber() + 1)
                .sort(sorts)
                .build();

        SearchResultPaginated result = (SearchResultPaginated) movieIndex.search(request);

        List<Movie> movies = result.getHits().stream()
                .map(item -> hitToMovieMapper(item))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new PageImpl<>(movies, pageable, result.getTotalHits());
    }

    @Override
    public void updateSortableAttributes(String[] attributes) {
        client.index("movies").updateSortableAttributesSettings(attributes);
    }

    private Movie hitToMovieMapper(HashMap<String, Object> hit){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(hit), Movie.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
