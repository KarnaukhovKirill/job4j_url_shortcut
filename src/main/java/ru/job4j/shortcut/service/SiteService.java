package ru.job4j.shortcut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.SiteRepository;
import java.util.Collections;

@Service
public class SiteService implements UserDetailsService {
    @Autowired
    private SiteRepository siteRepository;

    public Site save(Site site) {
        return siteRepository.save(site);
    }

    public Site findByLogin(String login) {
        return siteRepository.findSiteByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Site site = siteRepository.findSiteByLogin(login);
        if (site == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(site.getLogin(), site.getPassword(), Collections.emptyList());
    }
}
