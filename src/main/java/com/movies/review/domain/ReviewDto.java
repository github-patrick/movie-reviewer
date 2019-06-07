package com.movies.review.domain;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
