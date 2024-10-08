package com.UrlShortener.UrlShortenerService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UrlMapping {
    @Id
    private String shortUrl;
    private String originalUrl;

    public UrlMapping() {
    }

    public UrlMapping(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}

