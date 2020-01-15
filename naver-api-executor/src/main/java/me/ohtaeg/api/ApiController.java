package me.ohtaeg.api;

import me.ohtaeg.api.dto.BlogRequest;
import me.ohtaeg.api.dto.MovieRequest;
import me.ohtaeg.application.BlogService;
import me.ohtaeg.application.MovieService;
import me.ohtaeg.domain.response.Blog;
import me.ohtaeg.domain.response.Movie;
import me.ohtaeg.domain.response.SearchApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/with")
    public List<SearchApi> with(@RequestParam(required = true) String query) {
        List<SearchApi> searchApis = new ArrayList<>();
        Blog blog = (Blog) blogService.search(new BlogRequest(query));
        Movie movie = (Movie) movieService.search(new MovieRequest(query));

        searchApis.add(blog);
        searchApis.add(movie);
        return searchApis;
    }
}
