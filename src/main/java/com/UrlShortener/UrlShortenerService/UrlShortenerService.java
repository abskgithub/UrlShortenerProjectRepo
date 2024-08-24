package com.UrlShortener.UrlShortenerService;

import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlShortenerService {
    
    private final UrlRepository urlRepository;
    private final Map<String, Integer> domainCounter = new ConcurrentHashMap<>();

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

        String domain = UrlShortenerUtil.extractDomain(originalUrl);
        domainCounter.put(domain, domainCounter.getOrDefault(domain, 0) + 1);

        return shortUrl;
    }

    public Map<String, Integer> getTopDomains() {
        return domainCounter.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

    public String getOriginalUrl(String shortUrl) {
        UrlMapping urlMapping = urlRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
            return urlMapping.getOriginalUrl();
        }
        throw new NoSuchElementException("Short URL not found");
    }

}
