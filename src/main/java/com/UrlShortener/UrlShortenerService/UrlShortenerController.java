package com.UrlShortener.UrlShortenerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {
        return urlShortenerService.shortenUrl(originalUrl);
    }

    @GetMapping("/metrics")
    public ResponseEntity<?> getTopDomains() {
        return ResponseEntity.ok(urlShortenerService.getTopDomains());
    }
    
}
