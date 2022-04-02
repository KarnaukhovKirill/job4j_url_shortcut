package ru.job4j.shortcut.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String url;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Shortcut> shortcuts = new HashSet<>();

    public Site() {
    }

    public static Site of(String login, String password, String inputSite) {
        Site site = new Site();
        site.login = login;
        site.password = password;
        site.url = inputSite;
        return site;
    }

    public void addShortcut(Shortcut shortcut) {
        shortcuts.add(shortcut);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Shortcut> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(Set<Shortcut> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String site) {
        this.url = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site = (Site) o;
        return id == site.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Site{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", shortcuts=" + shortcuts
                + '}';
    }
}
