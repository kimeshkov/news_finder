package com.newstaker.rest;

import com.newstaker.domain.NewsMessage;
import com.newstaker.service.NewsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kimeshkov on 12.06.2016.
 */

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsSearchService newsSearchService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsMessage> getAllNews() {
        return newsSearchService.getAllSavedNews();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsMessage> searchNewsNow() {
        return newsSearchService.findAndSaveNews();
    }
}
