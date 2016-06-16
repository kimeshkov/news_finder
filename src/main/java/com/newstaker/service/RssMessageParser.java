package com.newstaker.service;

import com.newstaker.domain.NewsMessage;

import java.util.List;

/**
 * Created by kimeshkov on 14.06.2016.
 */
public interface RssMessageParser {

    List<NewsMessage> parseFromSourceUrl(String sourceUrl);
}
