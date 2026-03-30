package com.url.shortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.url.shortner.service.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    @ResponseBody
    public String shorten(@RequestParam String url) {

        String code = urlService.UrlShortner(url);

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        String shortUrl = baseUrl + "/" + code;

        return shortUrl; // ✅ IMPORTANT
    }

    @GetMapping("/{code}")
    public String redirect(@PathVariable String code) {
        String longUrl = urlService.getLongCode(code);
        if (longUrl == null)
            return "URL NOT FOUND !";
        return longUrl;
    }

    @GetMapping("/clicks/{code}")
    @ResponseBody
    public int clicks(@PathVariable String code) {
        return urlService.getClick(code);
    }
}