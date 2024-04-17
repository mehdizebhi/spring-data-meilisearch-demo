package dev.mehdizebhi.springdatameilisearchdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mehdizebhi.springdatameilisearchdemo.exception.GenericException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {

    private T content;
    private Map<String, Object> metadata;
    private Error error;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<T> {
        private T content;
        private Map<String, Object> metadata;
        private Error error;

        public Builder content(T content) {
            this.content = content;
            return this;
        }

        public Builder metadata(String key, Object value) {
            if (metadata == null) {
                this.metadata = new HashMap<>();
            }
            this.metadata.put(key, value);
            return this;
        }

        public Builder pageMetadata(Page<?> page) {
            metadata("size", page.getSize());
            metadata("page", page.getNumber());
            metadata("totalPages", page.getTotalPages());
            metadata("totalElements", page.getTotalElements());
            return this;
        }

        public Builder error(Error error) {
            this.error = error;
            return this;
        }

        public Builder error(GenericException exception, String endpoint) {
            this.error = Error.builder()
                    .title(exception.getTitle())
                    .detail(exception.getMessage())
                    .type(exception.getType().name())
                    .status(exception.getHttpStatus().value())
                    .instance(endpoint)
                    .build();
            return this;
        }

        public ResponseModel<T> build() {
            ResponseModel<T> responseModel = new ResponseModel<>();
            responseModel.setContent(content);
            responseModel.setMetadata(metadata);
            responseModel.setError(error);
            return responseModel;
        }
    }
}
