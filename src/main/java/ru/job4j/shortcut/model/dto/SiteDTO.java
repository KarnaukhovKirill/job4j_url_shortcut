package ru.job4j.shortcut.model.dto;

import javax.validation.constraints.NotNull;

public class SiteDTO {
    @NotNull
    private String site;

    public SiteDTO() {
    }

    public static SiteDTO of(String site) {
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.site = site;
        return siteDTO;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
