package com.newstaker.repository;

import com.newstaker.domain.NewsMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kimeshkov on 12.06.2016.
 */
public interface NewsMessageRepository extends CrudRepository<NewsMessage, Integer> {
    @Query("select newsMessage.guid from NewsMessage newsMessage")
    List<String> getAllGuids();
}
