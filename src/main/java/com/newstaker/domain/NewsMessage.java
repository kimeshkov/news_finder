package com.newstaker.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by kimeshkov on 12.06.2016.
 */

@Entity
@Table(
        name = "news_message",
        uniqueConstraints = @UniqueConstraint(columnNames = {"guid"})
)
public class NewsMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String guid;

    @Column
    private String title;

    @Column
    private String link;

    public Integer getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsMessage message = (NewsMessage) o;
        return Objects.equals(guid, message.guid) &&
                Objects.equals(title, message.title) &&
                Objects.equals(link, message.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, title, link);
    }
}
