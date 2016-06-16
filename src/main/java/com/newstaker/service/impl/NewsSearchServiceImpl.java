package com.newstaker.service.impl;

import com.google.common.collect.Lists;
import com.newstaker.domain.NewsMessage;
import com.newstaker.domain.Source;
import com.newstaker.repository.NewsMessageRepository;
import com.newstaker.repository.SourceRepository;
import com.newstaker.service.NewsSearchService;
import com.newstaker.service.RssMessageParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kimeshkov on 12.06.2016.
 */
@Service
public class NewsSearchServiceImpl implements NewsSearchService {
    private static final Log log = LogFactory.getLog(NewsSearchServiceImpl.class);

    @Autowired
    private NewsMessageRepository newsMessageRepository;

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private RssMessageParser rssMessageParser;

    @Override
    @Transactional(readOnly = true)
    public List<NewsMessage> getAllSavedNews() {
        return Lists.newArrayList(newsMessageRepository.findAll());
    }

    @Override
    @Transactional
    public List<NewsMessage> findAndSaveNews() {
        List<NewsMessage> messages = new ArrayList<>();

        for (Source source : getSourceUrls()) {
            messages.addAll(rssMessageParser.parseFromSourceUrl(source.getUrl()));
        }

        return Lists.newArrayList(newsMessageRepository.save(getOnlyNewMessages(messages)));
    }

    private List<NewsMessage> getOnlyNewMessages(List<NewsMessage> newsMessages) {
        List<String> guids = newsMessageRepository.getAllGuids();
        return newsMessages.stream().filter(newsMessage -> !guids.contains(newsMessage.getGuid())).collect(Collectors.toList());
    }

    private Iterable<Source> getSourceUrls() {

        Source source = new Source();
        source.setUrl("https://lenta.ru/rss/news");
        return Lists.newArrayList(source);
        //return sourceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsMessage> getAllExistingGuids(List<String> guids) {
        return null;
    }

}
