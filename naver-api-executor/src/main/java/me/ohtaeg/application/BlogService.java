package me.ohtaeg.application;

import config.NaverApiProperties;
import me.ohtaeg.api.dto.BlogRequest;
import me.ohtaeg.api.dto.SearchWord;
import me.ohtaeg.domain.response.Blog;
import me.ohtaeg.domain.repository.SearchRepository;
import me.ohtaeg.domain.response.OpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService implements SearchService {
    @Autowired
    private NaverApiProperties naverApiProperties; // TODO : Refcator
    private SearchRepository searchRepository;

    public BlogService(final SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public OpenApi search(final SearchWord request) {
        BlogRequest blogRequest = (BlogRequest)request;
        Blog blog = new Blog(blogRequest, naverApiProperties.getUrl());
        return searchRepository.search(blog);
    }
}
