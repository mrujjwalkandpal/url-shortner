package com.url.shortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortner.repository.UrlRepository;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private String generateCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }

    public String UrlShortner(String longURL) {

        longURL = longURL.trim();

        if (!longURL.toLowerCase().startsWith("http://") &&
                !longURL.toLowerCase().startsWith("https://")) {
            longURL = "https://" + longURL;
        }

        String shortCode = generateCode();

        while (urlRepository.existsByShortCode(shortCode)) {
            shortCode = generateCode();
        }

        urlRepository.save(shortCode, longURL);
        return shortCode;
    }

    public String getLongCode(String shortCode) {
        String x = urlRepository.findLongUrl(shortCode);
        if (x != null) {
            urlRepository.incrementClickCount(shortCode);
        }
        return x;
    }

    public int getClick(String shortCode) {
        return urlRepository.getClickCount(shortCode);
    }

}
