package com.movies.film.domain;

import com.movies.rating.domain.Rating;
import com.movies.review.domain.Review;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Identifies the association of Film to a {@link Review} and a
 * {@link Rating} .
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILM_ID")
    private Long id;
    private String title;
    private int year;
    private double avgScore;
    private int aggregateRating;
    private byte[] filmPicture;

    @OneToMany(mappedBy = "film")
    private List<Review> reviews;

    @OneToMany(mappedBy = "film")
    private List<Rating> ratings;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
