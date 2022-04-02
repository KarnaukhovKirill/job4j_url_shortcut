package ru.job4j.shortcut.model.dto;

public class StatisticDTO {
    private String url;
    private long total;

    public static StatisticDTO of(String url, long total) {
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.url = url;
        statisticDTO.total = total;
        return statisticDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
