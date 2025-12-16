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
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        log.info("Saving actor: name={}, birthday={}", actorDto.name(), actorDto.birthday());
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

        if (actorDto.discography() != null && !actorDto.discography().isEmpty()) {
            log.info("Saving initial discography entries: count={}", actorDto.discography().size());
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
        } else {
            log.info("No initial discography provided for actor {}", savedActor.getId());
        }
        return actorMapper.toDto(savedActor);
    }

    public ActorDto updateActor(int id, ActorDto actorDto) {
        log.info("Updating actor {}: name={}, birthday={}, biography={}", id, actorDto.name(), actorDto.birthday(), actorDto.biography());
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();

            actor.setName(actorDto.name());
            actor.setBiography(actorDto.biography());
            actor.setBirthday(actorDto.birthday());

            if (actorDto.discography() != null && !actorDto.discography().isEmpty()) {
                log.info("Updating actor {} discography via PUT /actors/{{id}}: entries={}", id, actorDto.discography().size());
                Set<Role> roles = new HashSet<>();
                for (RoleDto roleDto : actorDto.discography()) {
                    Role role = roleMapper.toRole(roleDto);
                    if (role.getActor() == null) {
                        role.setActor(actor);
                    }
                    if (role.getMovie() != null && roleRepository.findByActorIdAndMovieId(actor.getId(), role.getMovie().getId()).isPresent()) {
                        roles.add(role);
                    } else {
                        roleRepository.save(role);
                        roles.add(role);
                    }
                }
                actor.setDiscography(roles);
            } else {
                log.info("No discography changes provided for actor {}", id);
            }
            actorRepository.save(actor);

            return actorMapper.toDto(actor);

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
        log.info("Updating actor biography {}: name={}, birthday={}, biography={}", id, actorDTO.name(), actorDTO.birthday(), actorDTO.biography());
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
        log.info("Updating actor awards {}: incomingAwardsCount={}", id, actorDTO.awards() != null ? actorDTO.awards().size() : 0);
        Actor actor = getById(id);
        if (actorDTO.awards() != null && !actorDTO.awards().isEmpty()) {
            List<Award> awardList = actorDTO.awards().stream().map(awardMapper::toAward).toList();
            actor.getAwards().addAll(awardList);
            awardRepository.saveAll(awardList);
        } else {
            log.info("No awards provided to update for actor {}", id);
        }
        return actorMapper.toDto(actorRepository.save(actor));
    }

    public ActorDto updateActorDiscography(int id, ActorDto actorDTO) {
        log.info("Updating actor discography {}: incomingRolesCount={}", id, actorDTO.discography() != null ? actorDTO.discography().size() : 0);
        Actor actor = getById(id);

        if (actorDTO.discography() == null || actorDTO.discography().isEmpty()) {
            log.info("No discography entries provided for actor {}", id);
            return actorMapper.toDto(actor);
        }

        Map<Movie, String> movieList = actorDTO.discography().stream()
                .filter(roleDTO -> roleDTO.movie() != null && roleDTO.movie().name() != null)
                .collect(Collectors.toMap(
                        roleDTO -> movieMapper.toMovie(roleDTO.movie()),
                        RoleDto::character
                ));
        Role role;
        List<Role> roles = new ArrayList<>();
        Movie findMovie;
        for (var movie : movieList.entrySet()) {
            findMovie = movieRepository.findMovieByNameAndYear(movie.getKey().getName(), movie.getKey().getYear());
            if (findMovie == null) {
                Director director = movie.getKey().getDirector();
                if (director != null && director.getName() != null && !director.getName().isBlank()) {
                    Optional<Director> findDirector = directorRepository.findDirectorByName(director.getName());
                    if (findDirector.isEmpty()) {
                        directorRepository.save(director);
                        log.info("Saved new director '{}'", director.getName());
                    } else {
                        movie.getKey().setDirector(findDirector.get());
                    }
                }
                movieRepository.save(movie.getKey());
                findMovie = movie.getKey();
                log.info("Saved new movie '{}', year={}", findMovie.getName(), findMovie.getYear());
            }
            role = new Role();

            role.setActor(actor);
            role.setMovie(findMovie);
            role.setCharacter(movie.getValue());
            roleRepository.save(role);
            roles.add(role);
            log.info("Linked actor {} to movie '{}' with character '{}'", actor.getId(), findMovie.getName(), movie.getValue());
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
