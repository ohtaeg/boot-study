package me.ohtaeg.domain.repository;

import me.ohtaeg.domain.response.OpenApi;
import org.springframework.http.HttpHeaders;

public interface SearchRepository<T extends OpenApi> {
    String UTF_8 = "UTF-8";

    T search(T obj);

    // TODO : refactor - repo interface가 header에 대한 default 메서드를 갖고있는게 맞을까?
    default HttpHeaders getHeader(String id, String secret) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", id);
        httpHeaders.add("X-Naver-Client-Secret", secret);
        return httpHeaders;
    }
}
