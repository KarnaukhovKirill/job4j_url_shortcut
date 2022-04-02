package ru.job4j.shortcut.model.dto;

import javax.validation.constraints.NotNull;

public class ShortcutDTO {
    @NotNull
    private String url;

    public ShortcutDTO() {
    }

    public static ShortcutDTO of(String url) {
        ShortcutDTO shortcutDTO = new ShortcutDTO();
        shortcutDTO.url = url;
        return shortcutDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
