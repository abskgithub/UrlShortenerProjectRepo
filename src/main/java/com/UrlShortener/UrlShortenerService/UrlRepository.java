package com.UrlShortener.UrlShortenerService;

import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlMapping, String> {
    UrlMapping findByOriginalUrl(String originalUrl);
    UrlMapping findByShortUrl(String shortUrl);
}
