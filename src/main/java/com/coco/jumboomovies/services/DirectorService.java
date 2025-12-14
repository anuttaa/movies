package com.coco.jumboomovies.services;

import com.coco.jumboomovies.dtos.AwardDto;
import com.coco.jumboomovies.dtos.DirectorDto;
import com.coco.jumboomovies.dtos.MovieDto;
import com.coco.jumboomovies.entities.Director;
import com.coco.jumboomovies.mappers.AwardMapper;
import com.coco.jumboomovies.mappers.DirectorMapper;
import com.coco.jumboomovies.mappers.MovieMapper;
import com.coco.jumboomovies.repositories.DirectorRepository;
import com.coco.jumboomovies.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;
    private final DirectorMapper directorMapper;
    private final MovieMapper movieMapper;
    private final AwardMapper awardMapper;

    public List<DirectorDto> getAllDirectors() {
        List<Director> directors = (List<Director>) directorRepository.findAll();
        return directors.stream().map(directorMapper::toDto).toList();
    }

    public DirectorDto getDirectorById(int id) {
        return directorRepository.findById(id)
                .map(directorMapper::toDto)
                .orElseThrow(() -> new com.coco.jumboomovies.exceptions.ResourceNotFoundException("Director not found with ID: " + id));
    }

    public List<DirectorDto> getDirectorsByName(String name) {
        return directorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(directorMapper::toDto)
                .toList();
    }

    public List<MovieDto> getAllDirectorMovies(int directorId) {
        return movieRepository.findMoviesByDirectorId(directorId)
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public List<AwardDto> getAllDirectorAwards(int directorId) {
        return directorRepository.findAwardsByDirectorId(directorId)
                .stream()
                .map(awardMapper::toDto)
                .toList();
    }
}
