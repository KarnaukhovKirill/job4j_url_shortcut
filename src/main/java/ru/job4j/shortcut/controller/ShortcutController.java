package ru.job4j.shortcut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.model.Shortcut;
import ru.job4j.shortcut.service.ShortcutService;
import java.net.URI;

@RestController
@RequestMapping("/")
public class ShortcutController {
    @Autowired
    private ShortcutService shortcutService;

    @GetMapping("/redirect/{code}")
    public ResponseEntity redirect(@PathVariable String code) {
        Shortcut shortcut = shortcutService.findByCode(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(shortcut.getUrl()));
        headers.set("HTTP " + shortcut.getCode(), "302 REDIRECT " + shortcut.getUrl());
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }
}
