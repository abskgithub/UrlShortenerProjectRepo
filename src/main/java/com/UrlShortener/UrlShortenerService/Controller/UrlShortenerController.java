package com.UrlShortener.UrlShortenerService.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.UrlShortener.UrlShortenerService.Service.UrlShortenerService;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/original/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
        return ResponseEntity.ok(originalUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalUrl);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
    
}
