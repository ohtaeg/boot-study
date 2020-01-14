package me.ohtaeg.api;

import me.ohtaeg.api.dto.BlogRequest;
import me.ohtaeg.application.SearchService;
import me.ohtaeg.domain.response.Blog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    private SearchService searchService;

    public ApiController(final SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/blog")
    public Blog blog(@Valid BlogRequest blogRequest) {
        return (Blog) searchService.search(blogRequest);
    }
}
