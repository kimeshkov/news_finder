package com.newstaker.service.impl;

import com.newstaker.service.NewsSearchJob;
import com.newstaker.service.NewsSearchService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by kimeshkov on 14.06.2016.
 */
@Service
public class NewsSearchJobImpl implements NewsSearchJob {
    private static final Log log = LogFactory.getLog(NewsSearchJobImpl.class);

    @Autowired
    private NewsSearchService newsSearchService;

    /**
     * This method is triggered every 15 minutes
     */

    @Override
    @Scheduled(cron = "0 0 12 * * ?")
    public void searchForNews() {
        log.info("Job started");
        newsSearchService.findAndSaveNews();
    }

}
