package com.movies.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.movies.rating.domain.RatingDto;
import com.movies.review.domain.ReviewDto;
import lombok.*;

import javax.validation.constraints.*;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private Long id;

    @NotBlank(message = "{user.name.error}")
    private String name;

    @Email(message = "{user.email.error}")
    private String email;

    @Size(min=6, message = "{user.password.error}" )
    private String password;

    private boolean enabled;

    @NotNull(message = "{user.accounttype.error}")
    private AccountType accountType;

    private List<ReviewDto> reviews;

    private List<RatingDto> ratings;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;



}
