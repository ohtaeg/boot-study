package me.ohtaeg.domain.response;

import me.ohtaeg.api.dto.BlogRequest;
import me.ohtaeg.api.dto.SearchWord;
import me.ohtaeg.domain.response.constant.RequestVariableName;
import me.ohtaeg.util.PropertyUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

public class Blog extends SearchApi implements Serializable {

    private static final long serialVersionUID = 1576360151347505333L;
    private static final String SEARCH_RESOURCE = "/blog";

    public Blog() {
    }

    public Blog(final BlogRequest blogRequest) {
        super.uri = createUri(blogRequest);
    }

    @Override
    String createUri(final SearchWord searchWord) {
        BlogRequest blogRequest = (BlogRequest) searchWord;
        return UriComponentsBuilder.fromHttpUrl(PropertyUtils.getUrl() + SEARCH_RESOURCE)
                .queryParam(RequestVariableName.QUERY.getName(), blogRequest.getQuery())
                .queryParam(RequestVariableName.DISPLAY.getName(), blogRequest.getDisplay())
                .queryParam(RequestVariableName.START.getName(), blogRequest.getStart())
                .queryParam(RequestVariableName.SORT.getName(), blogRequest.getSort())
                .build().toUriString();
    }
}
