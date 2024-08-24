package com.UrlShortener.UrlShortenerService;

import com.UrlShortener.UrlShortenerService.Controller.UrlShortenerController;
import com.UrlShortener.UrlShortenerService.Service.UrlShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Map;

class UrlShortenerControllerTest {

    @Mock
    private UrlShortenerService urlShortenerService;

    @InjectMocks
    private UrlShortenerController urlShortenerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShortenUrl() throws Exception {
        String originalUrl = "https://example.com";
        String shortUrl = "abc123";

        when(urlShortenerService.shortenUrl(originalUrl)).thenReturn(shortUrl);

        standaloneSetup(urlShortenerController)
            .build()
            .perform(post("/api/shorten").content(originalUrl))
            .andExpect(status().isOk())
            .andExpect(content().string(shortUrl));

        verify(urlShortenerService, times(1)).shortenUrl(originalUrl);
    }

    @Test
    void testRedirectToOriginalUrl() throws Exception {
        String shortUrl = "abc123";
        String originalUrl = "https://example.com";

        when(urlShortenerService.getOriginalUrl(shortUrl)).thenReturn(originalUrl);

        standaloneSetup(urlShortenerController)
            .build()
            .perform(get("/api/" + shortUrl))
            .andExpect(status().isFound())
            .andExpect(header().string("Location", originalUrl));

        verify(urlShortenerService, times(1)).getOriginalUrl(shortUrl);
    }

    @Test
    void testGetTopDomains() throws Exception {
        when(urlShortenerService.getTopDomains()).thenReturn(Map.of("example.com", 2, "another.com", 1));

        standaloneSetup(urlShortenerController)
            .build()
            .perform(get("/api/metrics"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.['example.com']").value(2))
            .andExpect(jsonPath("$.['another.com']").value(1));

        verify(urlShortenerService, times(1)).getTopDomains();
    }
}
