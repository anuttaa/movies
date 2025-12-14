package com.example.movies.services;

import com.example.movies.dtos.MovieDto;
import com.example.movies.dtos.UserDto;
import com.example.movies.dtos.UserProfileDto;
import com.example.movies.entities.User;
import com.example.movies.exceptions.ResourceNotFoundException;
import com.example.movies.mappers.MovieMapper;
import com.example.movies.mappers.UserMapper;
import com.example.movies.mappers.UserProfileMapper;
import com.example.movies.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MovieMapper movieMapper;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    public List<MovieDto> getUserFavoriteMovies(Integer userId) {
        return userRepository.findFavouriteMoviesByUserId(userId)
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public List<UserDto> findUserByName(String userName) {
        return userRepository.findByNameOrSurnameContaining(userName)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto findUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    public UserProfileDto findUserProfileById(Integer userId) {
        return userRepository.findById(userId)
                .map(userProfileMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with ID: " + userId));
    }

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserProfileDto updateUser(UserDto editedUserDto, MultipartFile avatar) {
        User user = userRepository.findById(editedUserDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + editedUserDto.id()));
        user.setName(editedUserDto.name());
        user.setSurname(editedUserDto.surname());
        String login = user.getLogin();
        //Photo saving
        if (avatar != null && !avatar.isEmpty()) {
            String uniqueFileName = login.substring(0, login.indexOf("@")) + "_" + avatar.getOriginalFilename();
            user.setAvatar("uploads/avatars/" + uniqueFileName);
            logger.info("Avatar isn't null, 🌊🔥♨ starting saving photos");
            savePhoto(avatar,uniqueFileName);
        }

        user = userRepository.save(user);
        return userProfileMapper.toDto(user);
    }

    private static void savePhoto(MultipartFile avatar, String uniqueFileName) {
        Path photoDirectory = Paths.get("uploads", "avatars");
        try {
            // Create the directory if it doesn't exist
            Files.createDirectories(photoDirectory);
            // Generate a unique filename
            Path photoPath = photoDirectory.resolve(uniqueFileName);
            // Save the photo to the file system
            Files.write(photoPath, avatar.getBytes(), StandardOpenOption.CREATE);
            logger.info("Photo 🎵🎶🎵 saved to " + photoPath);
        } catch (IOException e) {
            logger.error("WTF JUST HAPPENED");
            throw new RuntimeException("Error saving photo: " + e.getMessage(), e);
        }
    }
}
