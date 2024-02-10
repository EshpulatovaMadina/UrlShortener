package com.example.shortener.service;

import com.example.shortener.DTO.request.UrlShortenDto;
import com.example.shortener.DTO.response.ShortenResponse;
import com.example.shortener.entity.Visits;

import java.util.List;

public interface UrlService {
    ShortenResponse shorten(UrlShortenDto urlShorten);

    List<Visits> getInfo(String shortUrl, String code);

    String redirect(String shortUrl, String ipAddress);
}
