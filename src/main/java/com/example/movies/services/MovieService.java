package com.example.movies.services;

import com.example.movies.dtos.ActorDto;
import com.example.movies.dtos.AwardDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.dtos.RoleDto;
import com.example.movies.entities.Award;
import com.example.movies.entities.Director;
import com.example.movies.entities.Movie;
import com.example.movies.exceptions.ResourceNotFoundException;
import com.example.movies.mappers.AwardMapper;
import com.example.movies.mappers.MovieMapper;
import com.example.movies.mappers.RoleMapper;
import com.example.movies.repositories.AwardRepository;
import com.example.movies.repositories.DirectorRepository;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

            log.info("Updating movie {}: name={}, year={}, rate={}", id, movieDto.name(), movieDto.year(), movieDto.rate());
            movie.setName(movieDto.name());
            movie.setDescription(movieDto.description());
            movie.setPoster(movieDto.poster());

            Director incomingDirector = movieMapper.directorDtoToDirector(movieDto.director());
            if (incomingDirector != null) {
                Optional<Director> found = directorRepository.findDirectorByName(incomingDirector.getName());
                if (found.isPresent()) {
                    movie.setDirector(found.get());
                } else {
                    directorRepository.save(incomingDirector);
                    movie.setDirector(incomingDirector);
                }
            }
            movie.setRate(movieDto.rate());

            if (movieDto.awards() != null && !movieDto.awards().isEmpty()) {
                List<Award> awards = new ArrayList<>(movie.getAwards() != null ? movie.getAwards().size() : 0);
                for (AwardDto awardDto : movieDto.awards()) {
                    Award award = awardMapper.toAward(awardDto);
                    if (award.getId() != 0 && awardRepository.existsById(award.getId())) {
                        awards.add(award);
                    } else {
                        awardRepository.save(award);
                        awards.add(award);
                    }
                }
                movie.setAwards(awards);
            } else {
                log.info("No awards provided for movie {}, leaving awards unchanged", id);
            }
            movie.setYear(movieDto.year());
            movie.setDuration(movieDto.duration());
            if (movieDto.genres() != null && !movieDto.genres().isEmpty()) {
                movie.setGenres(movieDto.genres());
            }

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
