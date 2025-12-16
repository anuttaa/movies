package com.example.movies.services;

import com.example.movies.dtos.AwardDto;
import com.example.movies.dtos.DirectorDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.entities.Director;
import com.example.movies.mappers.AwardMapper;
import com.example.movies.mappers.DirectorMapper;
import com.example.movies.mappers.MovieMapper;
import com.example.movies.exceptions.ResourceNotFoundException;
import com.example.movies.repositories.DirectorRepository;
import com.example.movies.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with ID: " + id));
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

    public DirectorDto updateDirector(int id, DirectorDto directorDto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with ID: " + id));
        director.setName(directorDto.name());
        Director saved = directorRepository.save(director);
        return directorMapper.toDto(saved);
    }
}
