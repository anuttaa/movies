package com.coco.jumboomovies.mappers;

import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.dtos.ReviewDto;
import com.coco.jumboomovies.dtos.UserDto;
import com.coco.jumboomovies.entities.Movie;
import com.coco.jumboomovies.entities.Review;
import com.coco.jumboomovies.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "favourites", ignore = true)
    UserDto userToUserDto(User user);

    ReviewDto toDto(Review review);

    Review toReview(ReviewDto reviewDto);

    @Mappings({
        @Mapping(target = "director", ignore = true),
        @Mapping(target = "awards", ignore = true)
    })
    MovieDto movieToMovieDto(Movie movie);
}
