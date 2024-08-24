package com.UrlShortener.UrlShortenerService;

import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {
    
    private final UrlRepository urlRepository;

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {
        UrlMapping urlMapping = urlRepository.findByOriginalUrl(originalUrl);
        if (urlMapping != null) {
            return urlMapping.getShortUrl();
        }

        String shortUrl = UrlShortenerUtil.generateShortUrl(originalUrl);
        urlRepository.save(new UrlMapping(originalUrl, shortUrl));

        return shortUrl;
    }

}
