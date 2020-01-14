package me.ohtaeg.application;

import me.ohtaeg.api.dto.SearchWord;
import me.ohtaeg.domain.response.OpenApi;

public interface SearchService {
    OpenApi search(SearchWord searchWord);
}
