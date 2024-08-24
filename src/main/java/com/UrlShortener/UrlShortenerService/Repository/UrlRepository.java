package com.UrlShortener.UrlShortenerService.Repository;

import org.springframework.data.repository.CrudRepository;

import com.UrlShortener.UrlShortenerService.Model.UrlMapping;

public interface UrlRepository extends CrudRepository<UrlMapping, String> {
    UrlMapping findByOriginalUrl(String originalUrl);
    UrlMapping findByShortUrl(String shortUrl);
}
