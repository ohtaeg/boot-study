package me.ohtaeg.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.ohtaeg.api.dto.SearchWord;

public abstract class SearchApi {
    @JsonIgnore
    String uri;
    Integer total;
    Integer display;

    abstract String createUri(SearchWord searchWord);

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(final Integer display) {
        this.display = display;
    }
}
