package me.ohtaeg.infra;

import config.NaverApiProperties;
import me.ohtaeg.domain.repository.SearchRepository;
import me.ohtaeg.domain.response.OpenApi;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ApiDao implements SearchRepository {

    // TODO : refactoring - Repository가 properties와 restTemplate를 갖고있는게 맞을까?
    private NaverApiProperties naverApiProperties;
    private RestTemplate restTemplate;

    public ApiDao(NaverApiProperties naverApiProperties, RestTemplate restTemplate) {
        this.naverApiProperties = naverApiProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public OpenApi search(final OpenApi openApi) {
        HttpHeaders httpHeaders = getHeader(naverApiProperties.getClientId(), naverApiProperties.getClientSecret());
        return restTemplate.exchange(openApi.getUri()
                , HttpMethod.GET
                , new HttpEntity<>(httpHeaders)
                , openApi.getClass()).getBody();
    }
}
