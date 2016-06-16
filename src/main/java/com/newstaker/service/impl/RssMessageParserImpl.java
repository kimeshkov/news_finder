package com.newstaker.service.impl;

import com.newstaker.domain.NewsMessage;
import com.newstaker.service.RssMessageParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kimeshkov on 14.06.2016.
 */

@Service
public class RssMessageParserImpl implements RssMessageParser {
    private static final Log log = LogFactory.getLog(RssMessageParserImpl.class);

    private static final String ITEM_TAG = "item";
    private static final String GUID_TAG = "guid";
    private static final String TITLE_TAG = "title";
    private static final String LINK_TAG = "link";

    private static final int NEWS_LIMIT = 10;

    @Override
    public List<NewsMessage> parseFromSourceUrl(String sourceUrl) {
        try {
            URL recourseUrl = new URL(sourceUrl);

            log.info("Sending 'GET' request to URL : " + sourceUrl);
            HttpURLConnection connection = (HttpURLConnection) recourseUrl.openConnection();

            int responseCode = connection.getResponseCode();
            log.info("Response Code : " + responseCode);

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new InputStreamReader(connection.getInputStream()));

            List<NewsMessage> messages = new ArrayList<>();

            boolean isInItemTag = false;
            NewsMessage message = null;
            int newsCount = 0;
            while (xmlEventReader.hasNext() && newsCount < NEWS_LIMIT) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    String tagName = xmlEvent.asStartElement().getName().getLocalPart();
                    if (ITEM_TAG.equals(tagName)) {
                        message = new NewsMessage();
                        isInItemTag = true;
                    } else if (isInItemTag && GUID_TAG.equals(tagName)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        message.setGuid(xmlEvent.asCharacters().getData());
                    } else if (isInItemTag && TITLE_TAG.equals(tagName)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        message.setTitle(xmlEvent.asCharacters().getData());
                    } else if (isInItemTag && LINK_TAG.equals(tagName)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        message.setLink(xmlEvent.asCharacters().getData());
                    }
                }

                if (xmlEvent.isEndElement()) {
                    String tagName = xmlEvent.asEndElement().getName().getLocalPart();
                    if (ITEM_TAG.equals(tagName)) {
                        messages.add(message);
                        isInItemTag = false;
                        newsCount++;
                    }
                }
            }

            return messages;

        } catch (Exception e) {
            log.error("Exception during processing", e);
            return Collections.emptyList();
        }
    }
}
