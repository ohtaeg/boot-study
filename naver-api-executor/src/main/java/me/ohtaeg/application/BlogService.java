package me.ohtaeg.application;

import me.ohtaeg.api.dto.BlogRequest;
import me.ohtaeg.api.dto.SearchWord;
import me.ohtaeg.domain.repository.SearchRepository;
import me.ohtaeg.domain.response.Blog;
import me.ohtaeg.domain.response.SearchApi;
import org.springframework.stereotype.Service;

@Service
public class BlogService implements SearchService {

    private SearchRepository searchRepository;

    public BlogService(final SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public SearchApi search(final SearchWord request) {
        BlogRequest blogRequest = (BlogRequest)request;
        return searchRepository.search(new Blog(blogRequest));
    }
}
