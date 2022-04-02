package ru.job4j.shortcut.controller;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.Shortcut;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.model.dto.ShortcutDTO;
import ru.job4j.shortcut.model.dto.SiteDTO;
import ru.job4j.shortcut.model.dto.StatisticDTO;
import ru.job4j.shortcut.service.SiteService;
import ru.job4j.shortcut.util.GeneratorRandomString;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@PropertySource("classpath:application.properties")
public class SiteController {
    @Autowired
    private SiteService service;
    @Value("${generator.chars.in.password}")
    private String charsInPassword;
    @Value("${generator.chars.in.code}")
    private String charsInCode;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/registration")
    public ResponseEntity registration(@Valid @RequestBody SiteDTO siteDTO) {
        Site site;
        String password = GeneratorRandomString.ofPassword(Integer.valueOf(charsInPassword));
        if (siteDTO.getSite().startsWith("http://") || siteDTO.getSite().startsWith("https://")) {
            site = constructSite(siteDTO, password);
        } else {
            siteDTO.setSite("http://".concat(siteDTO.getSite()));
            site = constructSite(siteDTO, password);
        }
        Map<String, String> body = constructBody(site, password);
        return new ResponseEntity(body, HttpStatus.CREATED);
    }

    private Site constructSite(SiteDTO siteDTO, String password) {
        Site site = new Site();
        if (UrlValidator.getInstance().isValid(siteDTO.getSite())) {
            site.setUrl(siteDTO.getSite());
            site.setLogin(siteDTO.getSite().split("://")[1]);
            site.setPassword(encoder.encode(password));
        } else {
            throw new IllegalArgumentException("Url сайта, который вы указали не существует");
        }
        return site;
    }

    private Map<String, String> constructBody(Site site, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("login", site.getLogin());
        body.put("password", password);
        String result = (service.save(site) != null) ? "true" : "false";
        body.put("registration", result);
        return body;
    }

    @PostMapping("/convert")
    public ResponseEntity convert(@Valid @RequestBody ShortcutDTO shortcutDTO) {
        if (UrlValidator.getInstance().isValid(shortcutDTO.getUrl())) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String login = authentication.getName();
            if (shortcutDTO.getUrl().contains(login)) {
                Site site = service.findByLogin(login);
                Shortcut shortcut = Shortcut.of(GeneratorRandomString.ofCode(Integer.valueOf(charsInCode)),
                        shortcutDTO.getUrl());
                site.addShortcut(shortcut);
                service.save(site);
                return new ResponseEntity(Map.of("code", shortcut.getCode()), HttpStatus.CREATED);
            } else {
                throw new IllegalArgumentException("Вы пытаетесь зарегистрировать url не своего домена. "
                        + "Ваш домен " + login);
            }
        } else {
            throw new IllegalArgumentException("URL " + shortcutDTO.getUrl() + " не существует");
        }
    }

    @GetMapping("/statistic")
    public ResponseEntity getStatistic() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Site site = service.findByLogin(login);
        var body = site.getShortcuts().stream()
                .map(shortcut -> StatisticDTO.of(shortcut.getUrl(), shortcut.getCounter()))
                .collect(Collectors.toList());
        return new ResponseEntity(body, HttpStatus.OK);

    }
}
