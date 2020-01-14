package me.ohtaeg.infra;

import config.NaverApiProperties;
import me.ohtaeg.domain.response.Blog;
import me.ohtaeg.domain.repository.SearchRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class BlogRepository implements SearchRepository<Blog> {

    // TODO : refactoring - Repository가 properties와 restTemplate를 갖고있는게 맞을까?
    private NaverApiProperties naverApiProperties;
    private RestTemplate restTemplate;

    public BlogRepository(NaverApiProperties naverApiProperties, RestTemplate restTemplate) {
        this.naverApiProperties = naverApiProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public Blog search(final Blog blog) {
        HttpHeaders httpHeaders = getHeader(naverApiProperties.getClientId(), naverApiProperties.getClientSecret());
        return restTemplate.exchange(blog.getUri()
                , HttpMethod.GET
                , new HttpEntity<>(httpHeaders)
                , blog.getClass()).getBody();
    }
}
