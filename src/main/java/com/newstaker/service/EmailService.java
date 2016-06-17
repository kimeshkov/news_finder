package com.newstaker.service;

import com.newstaker.domain.NewsMessage;

/**
 * Created by kimeshkov on 17.06.2016.
 */
public interface EmailService {

    void sendNewsMessage(NewsMessage message);

}
