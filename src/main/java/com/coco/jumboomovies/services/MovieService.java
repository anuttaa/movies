package com.coco.jumboomovies.services;

import com.coco.jumboomovies.dtos.ActorDto;
import com.coco.jumboomovies.dtos.AwardDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.dtos.RoleDto;
import com.coco.jumboomovies.entities.Award;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.entities.Movie;
import com.coco.jumboomovies.exceptions.ResourceNotFoundException;
import com.coco.jumboomovies.mappers.AwardMapper;
import com.coco.jumboomovies.mappers.MovieMapper;
import com.coco.jumboomovies.mappers.RoleMapper;
import com.coco.jumboomovies.repositories.AwardRepository;
import com.coco.jumboomovies.repositories.DirectorRepository;
import com.coco.jumboomovies.repositories.MovieRepository;
import com.coco.jumboomovies.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final RoleRepository roleRepository;
    private final AwardRepository awardRepository;
    private final MovieMapper movieMapper;
    private final RoleMapper roleMapper;
    private final AwardMapper awardMapper;

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movieMapper::toDto).toList();
    }

    public List<MovieDto> getAllMoviesByName(String name) {
        return movieRepository.findMoviesByNameContainingIgnoreCase(name)
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public MovieDto getMovieById(int id) {
        Optional<MovieDto> optionalMovie = movieRepository.findById(id).map(movieMapper::toDto);
        return optionalMovie
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + id));
    }

    public MovieDto addMovie(MovieDto movieDto) {
        if (movieDto.director() == null) {
            throw new NullPointerException("Director field in null");
        }
        Movie movie = movieMapper.toMovie(movieDto);
        Director director = directorRepository
                .findDirectorByName(movie.getDirector().getName())
                .orElse(null);
        if (director != null) {
            movie.setDirector(director);
        } else {
            directorRepository.save(movie.getDirector());
        }
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }

    public MovieDto updateMovie(int id, MovieDto movieDto) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            movie.setName(movieDto.name());
            movie.setDescription(movieDto.description());

            Director director = directorRepository.findDirectorByName(movie.getDirector().getName()).orElse(null);
            if (director != null) {
                movie.setDirector(director);
            } else {
                directorRepository.save(movie.getDirector());
            }

            movie.setDirector(director);
            movie.setRate(movieDto.rate());

            List<Award> awards = new ArrayList<>(movie.getAwards().size());
            for (AwardDto awardDto : movieDto.awards()) {
                Award award = awardMapper.toAward(awardDto);
                if (awardRepository.existsById(award.getId())) {
                    awards.add(award);
                } else {
                    awardRepository.save(award);
                    awards.add(award);
                }
            }
            movie.setAwards(awards);
            movie.setYear(movieDto.year());
            movie.setDuration(movieDto.duration());
            movie.setGenres(movieDto.genres());

            movieRepository.save(movie);

            return movieMapper.toDto(movie);
        } else {
            throw new IllegalArgumentException("Movie with ID " + id + " not found.");
        }
    }

    public void deleteMovieById(int id) {
        if (movieRepository.existsById(id)) {
            try {
                movieRepository.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new IllegalArgumentException("Error deleting movie with ID: " + id, e);
            }
        } else {
            throw new IllegalArgumentException("Movie with ID " + id + " not found.");
        }
    }

    public List<ActorDto> getAllActorsFromMovie(int id) {
        return roleRepository.findRolesByMovieId(id)
                .stream()
                .map(roleMapper::toDto)
                .map(RoleDto::actor)
                .toList();
    }
}