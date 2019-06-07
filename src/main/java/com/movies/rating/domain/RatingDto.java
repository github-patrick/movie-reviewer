package com.movies.rating.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingDto {

    private Long id;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
