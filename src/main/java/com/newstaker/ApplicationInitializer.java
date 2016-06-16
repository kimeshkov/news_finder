package com.newstaker;

import com.newstaker.domain.Source;
import com.newstaker.repository.SourceRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is used to initialize application with default data
 */

@Component
public class ApplicationInitializer implements SmartInitializingSingleton {

    @Autowired
    private SourceRepository sourceRepository;

    @Override
    public void afterSingletonsInstantiated() {
        Source defaultSource = new Source();
        defaultSource.setUrl("https://lenta.ru/rss/news");
        sourceRepository.save(defaultSource);
    }
}
