package me.ohtaeg.domain.response;

import me.ohtaeg.api.dto.BlogRequest;
import me.ohtaeg.domain.response.constant.RequestVariableName;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

public class Blog extends OpenApi implements Serializable {

    private static final long serialVersionUID = 1576360151347505333L;

    public Blog() {
    }

    public Blog(final BlogRequest blogRequest, final String url) {
        super.uri = createUri(blogRequest, url);
    }

    private String createUri(final BlogRequest blogRequest, final String url) {
        return UriComponentsBuilder.fromHttpUrl(url)
                                   .queryParam(RequestVariableName.QUERY.getName(), blogRequest.getQuery())
                                   .queryParam(RequestVariableName.DISPLAY.getName(), blogRequest.getDisplay())
                                   .queryParam(RequestVariableName.START.getName(), blogRequest.getStart())
                                   .queryParam(RequestVariableName.SORT.getName(), blogRequest.getSort())
                                   .build().toUriString();
    }
}
