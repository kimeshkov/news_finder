package com.newstaker.service;

import com.newstaker.domain.NewsMessage;

import java.util.List;

/**
 * Created by kimeshkov on 12.06.2016.
 */
public interface NewsSearchService {

    /**
     * This method retrieves all existing news from db.
     * @return list of news
     * */
    List<NewsMessage> getAllSavedNews();

    /**
     * This method searches for news and saves it to db.
     *
     * @return list of found news
     * */
    List<NewsMessage> findAndSaveNews();

    List<NewsMessage> getAllExistingGuids(List<String> guids);
}
