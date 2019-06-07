package com.movies.user.domain;

import com.movies.rating.domain.RatingDto;
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
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;

    private boolean active = true;

    private List<ReviewDto> reviews;

    private List<RatingDto> ratings;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;



}
