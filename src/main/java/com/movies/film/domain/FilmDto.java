package com.movies.film.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.movies.rating.domain.Rating;
import com.movies.rating.domain.RatingDto;
import com.movies.review.domain.Review;
import com.movies.review.domain.ReviewDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmDto {

    private Long id;

    private String title;

    private int year;

    private byte[] filmPicture;

    private List<ReviewDto> reviews;

    private List<RatingDto> ratings;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
