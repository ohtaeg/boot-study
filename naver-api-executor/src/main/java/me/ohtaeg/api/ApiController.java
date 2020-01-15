package me.ohtaeg.api;

import me.ohtaeg.api.dto.BlogRequest;
import me.ohtaeg.api.dto.MovieRequest;
import me.ohtaeg.application.BlogService;
import me.ohtaeg.application.MovieService;
import me.ohtaeg.application.SearchService;
import me.ohtaeg.domain.response.Blog;
import me.ohtaeg.domain.response.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    private BlogService blogService;
    private MovieService movieService;

    public ApiController(final BlogService blogService, final MovieService movieService) {
        this.blogService = blogService;
        this.movieService = movieService;
    }

    @GetMapping("/blog")
    public Blog blog(@Valid BlogRequest blogRequest) {
        return (Blog) blogService.search(blogRequest);
    }

    @GetMapping("/movie")
    public Movie movie(@Valid MovieRequest movieRequest) {
        return (Movie) movieService.search(movieRequest);
    }
}
