package com.panamodels;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class ScrapperConfig {

    protected static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/45.0.2454.101 Safari/537.36";
    protected static final String REFERRER = "http://www.google.com";
    protected static final int TIMEOUT = 10 * 1000;

    protected Document connectWith(String link) throws IOException {
        return Jsoup.connect(link)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .timeout(TIMEOUT)
                .ignoreHttpErrors(true)
                .followRedirects(true)
                .get();
    }
}
