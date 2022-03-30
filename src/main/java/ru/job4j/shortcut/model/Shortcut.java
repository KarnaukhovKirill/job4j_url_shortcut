package ru.job4j.shortcut.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shortcuts")
public class Shortcut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String url;
    private long counter;

    public Shortcut() {
    }

    public static Shortcut of(String code, String url) {
        Shortcut shortcut = new Shortcut();
        shortcut.code = code;
        shortcut.url = url;
        shortcut.counter = 0;
        return shortcut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shortcut shortcut = (Shortcut) o;
        return id == shortcut.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
