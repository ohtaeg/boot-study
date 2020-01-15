package me.ohtaeg.domain.response;


import me.ohtaeg.api.dto.MovieRequest;
import me.ohtaeg.api.dto.SearchWord;
import me.ohtaeg.domain.response.constant.RequestVariableName;
import me.ohtaeg.util.PropertyUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class Movie extends SearchApi {
    private static final String SEARCH_RESOURCE = "/movie";
    private List<Item> items;

    public Movie() { }

    public Movie(final SearchWord searchWord) {
        super.uri = createUri(searchWord);
    }

    private static class Item {
        String title;
        String link;
        String subtitle;
        String actor;
        String userRating;

        public String getTitle() {
            return title;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(final String link) {
            this.link = link;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(final String subtitle) {
            this.subtitle = subtitle;
        }

        public String getActor() {
            return actor;
        }

        public void setActor(final String actor) {
            this.actor = actor;
        }

        public String getUserRating() {
            return userRating;
        }

        public void setUserRating(final String userRating) {
            this.userRating = userRating;
        }
    }

    public List<Item> getItems() {
        return items;
    }
    public void setItems(final List<Item> items) {
        this.items = items;
    }

    @Override
    String createUri(final SearchWord searchWord) {
        MovieRequest movieRequest = (MovieRequest) searchWord;
        return UriComponentsBuilder.fromHttpUrl(PropertyUtils.getUrl() + SEARCH_RESOURCE)
                .queryParam(RequestVariableName.QUERY.getName(), movieRequest.getQuery())
                .queryParam(RequestVariableName.DISPLAY.getName(), movieRequest.getDisplay())
                .queryParam(RequestVariableName.START.getName(), movieRequest.getStart())
                .build().toUriString();
    }
}
