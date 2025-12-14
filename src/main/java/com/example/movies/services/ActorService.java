package com.example.movies.services;

import com.example.movies.entities.*;
import com.example.movies.dtos.ActorDto;
import com.example.movies.dtos.AwardDto;
import com.example.movies.dtos.MovieDto;
import com.example.movies.dtos.RoleDto;
import com.example.movies.entities.*;
import com.example.movies.exceptions.ResourceNotFoundException;
import com.example.movies.mappers.MovieMapper;
import com.example.movies.mappers.ActorMapper;
import com.example.movies.mappers.AwardMapper;
import com.example.movies.mappers.RoleMapper;
import com.example.movies.repositories.*;
import com.example.movies.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
    private final RoleRepository roleRepository;
    private final MovieRepository movieRepository;
    private final ActorMapper actorMapper;
    private final MovieMapper movieMapper;
    private final AwardMapper awardMapper;
    private final RoleMapper roleMapper;
    private final AwardRepository awardRepository;
    private final DirectorRepository directorRepository;

    public Map<MovieDto, String> findMoviesByActorId(int actorId) {
        return actorRepository.findDiscographyByActorId(actorId)
                .stream()
                .collect(Collectors.toMap(
                        role -> movieMapper.toDto(role.getMovie()), // Key: MovieDto
                        Role::getCharacter // Value: Character
                ));
    }

    public List<ActorDto> findActorsByName(String name) {
        return actorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(actorMapper::toDto)
                .toList();
    }

    public List<ActorDto> getAllActors() {
        List<Actor> actors = (List<Actor>) actorRepository.findAll();
        return actors.stream().map(actorMapper::toDto).toList();
    }

    public ActorDto getActorById(int id) {
        Optional<ActorDto> actor =  actorRepository.findById(id).map(actorMapper::toDto);
        return actor.orElseThrow(() -> new ResourceNotFoundException("Actor not found with ID: " + id));
    }

    public ActorDto saveActor(ActorDto actorDto) {
        if (actorRepository.findByNameAndBirthday(actorDto.name(), actorDto.birthday()) != null) {
            throw new DataIntegrityViolationException(
                    "Actor with name "
                            + actorDto.name()
                            + " and birthday "
                            + actorDto.birthday()
                            + " already exists."
            );
        }
        Actor actor = actorMapper.toActor(actorDto);
        Actor savedActor = actorRepository.save(actor);

        if (actorDto.discography() != null) {
            for (RoleDto roleDto : actorDto.discography()) {
                Movie movie = movieRepository.findMovieByNameAndYear(roleDto.movie().name(), roleDto.movie().year());
                if (movie == null) {
                    movie = movieMapper.toMovie(roleDto.movie());
                    movieRepository.save(movie);
                }

                Role role = new Role();
                role.setActor(savedActor);
                role.setMovie(movie);
                role.setCharacter(roleDto.character());
                roleRepository.save(role);
            }
        }
        return actorMapper.toDto(savedActor);
    }

    public ActorDto updateActor(int id, ActorDto actorDto) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();

            actor.setName(actorDto.name());
            actor.setBiography(actorDto.biography());
            actor.setBirthday(actorDto.birthday());

            Set<Role> roles = new HashSet<>();
            for (RoleDto roleDto : actorDto.discography()) {
                Role role = roleMapper.toRole(roleDto);
                if (roleRepository.findByActorIdAndMovieId(role.getActor().getId(), role.getMovie().getId()).isPresent()) {
                    roles.add(role);
                } else {
                    roleRepository.save(role);
                    roles.add(role);
                }
            }
            actor.setDiscography(roles);
            actorRepository.save(actor);

            return actorDto;

        } else {
            throw new IllegalArgumentException("Actor with ID " + id + " not found.");
        }

    }

    public void deleteActorById(int id) {
        if (actorRepository.existsById(id)) {
            try {
                actorRepository.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new IllegalArgumentException("Error deleting actor with ID: " + id, e);
            }
        } else {
            throw new IllegalArgumentException("Actor with ID " + id + " not found.");
        }

    }

    public List<AwardDto> getAllActorsAwardById(int actorId) {
        return actorRepository.findAwardsByActorId(actorId).stream()
                .map(awardMapper::toDto)
                .toList();
    }

    public Map<String, String> getMovieRolesByActorId(int actorId) {
        List<RoleDto> roles = roleRepository.findRolesByActorId(actorId)
                .stream()
                .map(roleMapper::toDto)
                .toList();

        Map<String, String> movieRolesMap = new HashMap<>();
        for (RoleDto role : roles) {
            movieRolesMap.put(role.movie().name(), role.character());
        }

        return movieRolesMap;
    }

    public List<ActorDto> getAllActorsWithAward(String awardName) {
        return actorRepository.findAllByAwardType(awardName)
                .stream()
                .map(actorMapper::toDto)
                .toList();
    }

    public ActorDto updateActorBiography(int id, ActorDto actorDTO) {
        Actor actor = getById(id);

        if (actorDTO.name() != null) {
            actor.setName(actorDTO.name());
        }
        if (actorDTO.biography() != null) {
            actor.setBiography(actorDTO.biography());
        }
        if (actorDTO.birthday() != null) {
            actor.setBirthday(actorDTO.birthday());
        }

        Actor updatedActor = actorRepository.save(actor);
        return actorMapper.toDto(updatedActor);
    }

    public ActorDto updateActorAwards(int id, ActorDto actorDTO) {
        Actor actor = getById(id);
        List<Award> awardList = actorDTO.awards().stream().map(awardMapper::toAward).toList();
        actor.getAwards().addAll(awardList);
        awardRepository.saveAll(awardList);
        return actorMapper.toDto(actorRepository.save(actor));
    }

    public ActorDto updateActorDiscography(int id, ActorDto actorDTO) {
        Actor actor = getById(id);

        Map<Movie, String> movieList = actorDTO.discography().stream()
                .collect(Collectors.toMap(
                        roleDTO -> movieMapper.toMovie(roleDTO.movie()), // Map the movie DTO to the Movie entity
                        RoleDto::character // Get the associated character for each movie
                ));
        Role role;
        List<Role> roles = new ArrayList<>();
        Movie findMovie;
        for (var movie : movieList.entrySet()) {
            findMovie = movieRepository.findMovieByNameAndYear(movie.getKey().getName(), movie.getKey().getYear());
            if (findMovie == null) {
                Director director = movie.getKey().getDirector();
                Optional<Director> findDirector = directorRepository.findDirectorByName(director.getName());
                if (findDirector.isEmpty()) {
                    directorRepository.save(director);
                } else {
                    movie.getKey().setDirector(findDirector.get());
                }
                movieRepository.save(movie.getKey());
                findMovie = movie.getKey();
            }
            role = new Role();

            role.setActor(actor);
            role.setMovie(findMovie);
            role.setCharacter(movie.getValue());
            roleRepository.save(role);
            roles.add(role);
        }
        actor.getDiscography().addAll(roles);
        return actorMapper.toDto(actorRepository.save(actor));
    }

    private Actor getById(int id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor with ID " + id + " not found."));
    }

    public ActorDto getActorByName(String name) {
        return actorMapper.toDto(actorRepository.findByName(name));
    }
}
