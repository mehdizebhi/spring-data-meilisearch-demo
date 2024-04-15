package dev.mehdizebhi.springdatameilisearchdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    private Double id;
    private String title;
    private String overview;
    private List<String> genres;
    private String poster;
    private @JsonProperty("release_date") Double releaseDate;
}
