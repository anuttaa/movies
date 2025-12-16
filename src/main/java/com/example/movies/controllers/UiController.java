package com.example.movies.controllers;

import com.example.movies.dtos.MovieDto;
import com.example.movies.dtos.ReviewDto;
import com.example.movies.dtos.DirectorDto;
import com.example.movies.services.MovieService;
import com.example.movies.services.DirectorService;
import com.example.movies.services.ReviewService;
import com.example.movies.services.UserService;
import com.example.movies.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UiController {

    private final MovieService movieService;
    private final DirectorService directorService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final ActorService actorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Главная");
        try {
            List<MovieDto> movies = movieService.getAllMovies();
            model.addAttribute("movies", movies);
        } catch (Exception e) {
            model.addAttribute("movies", Collections.emptyList());
        }
        return "ui/index";
    }

    @GetMapping("/ui/movies")
    public String movies(Model model) {
        model.addAttribute("pageTitle", "Фильмы");
        model.addAttribute("movieId", null);
        return "ui/movies/movies";
    }

    @GetMapping("/ui/movies/{id}")
    public String movieDetails(@PathVariable int id, Model model) {
        model.addAttribute("pageTitle", "Фильмы");
        model.addAttribute("movieId", id);
        return "ui/movies/movies";
    }

    @GetMapping("/ui/actors")
    public String actors(Model model) {
        model.addAttribute("pageTitle", "Актёры");
        model.addAttribute("actorId", null);
        return "ui/actors/actors";
    }

    @GetMapping("/ui/actors/{id}")
    public String actorDetails(@PathVariable int id, Model model) {
        model.addAttribute("pageTitle", "Актёры");
        model.addAttribute("actorId", id);
        return "ui/actors/actors";
    }

    @GetMapping("/ui/directors")
    public String directors(Model model) {
        model.addAttribute("pageTitle", "Режиссёры");
        return "ui/directors/directors";
    }

    @GetMapping("/ui/directors/{id}")
    public String directorDetails(@PathVariable int id, Model model) {
        model.addAttribute("pageTitle", "Режиссёры");
        try {
            DirectorDto director = directorService.getDirectorById(id);
            model.addAttribute("director", director);
            model.addAttribute("movies", directorService.getAllDirectorMovies(id));
            model.addAttribute("awards", directorService.getAllDirectorAwards(id));
        } catch (Exception e) {
            model.addAttribute("director", null);
            model.addAttribute("movies", java.util.Collections.emptyList());
            model.addAttribute("awards", java.util.Collections.emptyList());
        }
        return "ui/directors/directors";
    }

    @GetMapping("/ui/awards")
    public String awards(Model model) {
        model.addAttribute("pageTitle", "Награды");
        return "ui/awards/awards";
    }

    @GetMapping("/ui/reviews")
    public String reviews(Model model) {
        model.addAttribute("pageTitle", "Отзывы");
        return "ui/reviews/reviews";
    }

    @GetMapping("/ui/reviews/{id}")
    public String reviewDetails(@PathVariable int id, Model model) {
        model.addAttribute("pageTitle", "Отзывы");
        ReviewDto review = reviewService.getReviewById(id).orElse(null);
        model.addAttribute("review", review);
        return "ui/reviews/reviews";
    }

    @GetMapping("/ui/favourites")
    public String favourites(Model model, @RequestParam(required = false) Integer userId) {
        model.addAttribute("pageTitle", "Избранное");
        model.addAttribute("userId", userId);
        if (userId != null) {
            try {
                model.addAttribute("favourites", userService.getFavourites(userId));
            } catch (Exception e) {
                model.addAttribute("favourites", java.util.Collections.emptyList());
            }
        }
        return "ui/favourites/index";
    }

    @GetMapping("/ui/users")
    public String users(Model model) {
        model.addAttribute("pageTitle", "Пользователи");
        model.addAttribute("userId", null);
        return "ui/users/users";
    }

    @GetMapping("/ui/users/{id}")
    public String userDetails(@PathVariable int id, Model model) {
        model.addAttribute("pageTitle", "Пользователи");
        model.addAttribute("userId", id);
        return "ui/users/users";
    }

    @GetMapping("/ui/auth/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Вход");
        return "ui/auth/login";
    }

    @GetMapping("/ui/auth/register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Регистрация");
        return "ui/auth/register";
    }

    @GetMapping("/ui/search")
    public String search(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("pageTitle", "Поиск");
        model.addAttribute("query", name);
        if (name != null && !name.isBlank()) {
            try {
                model.addAttribute("movies", movieService.getAllMoviesByName(name));
            } catch (Exception e) {
                model.addAttribute("movies", java.util.Collections.emptyList());
            }
            try {
                model.addAttribute("actors", actorService.findActorsByName(name));
            } catch (Exception e) {
                model.addAttribute("actors", java.util.Collections.emptyList());
            }
            try {
                model.addAttribute("directors", directorService.getDirectorsByName(name));
            } catch (Exception e) {
                model.addAttribute("directors", java.util.Collections.emptyList());
            }
        }
        return "ui/search/index";
    }
}
