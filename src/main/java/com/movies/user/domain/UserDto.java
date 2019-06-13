package com.movies.user.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.movies.rating.domain.RatingDto;
import com.movies.review.domain.ReviewDto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private Long id;

    @NotBlank(message = "{user.name.error}")
    private String name;

    @Email(message = "{user.email.error}")
    private String email;

    @Size(min=6, message = "{user.password.error}" )
    private String password;

    private boolean enabled = true;
    private AccountType accountType;


    private List<ReviewDto> reviews;

    private List<RatingDto> ratings;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;



}
