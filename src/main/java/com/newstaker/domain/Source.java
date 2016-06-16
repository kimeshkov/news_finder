package com.newstaker.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by kimeshkov on 15.06.2016.
 */
@Entity
@Table(name = "source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String url;

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(url, source.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
