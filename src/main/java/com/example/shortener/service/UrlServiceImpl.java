package com.example.shortener.service;

import com.example.shortener.DTO.request.UrlShortenDto;
import com.example.shortener.DTO.response.ShortenResponse;
import com.example.shortener.entity.UrlEntity;
import com.example.shortener.entity.Visits;
import com.example.shortener.exceptions.DataNotFoundException;
import com.example.shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    @Override
    public ShortenResponse shorten(UrlShortenDto urlShorten) {
        String generatedString = RandomStringUtils.randomAlphanumeric(8);
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrl(urlShorten.getUrl());
        urlEntity.setShortUrl(urlShorten.getShortUrl());
        urlEntity.setCode(generatedString);
        urlEntity.setVisits(new ArrayList<>());
        UrlEntity entity = urlRepository.save(urlEntity);
        return new ShortenResponse(entity.getUrl(),entity.getShortUrl(),entity.getCode());
    }

    @Override
    public List<Visits> getInfo(String shortUrl, String code) {
        UrlEntity urlEntity = urlRepository.findByShortUrlAndCode(shortUrl, code)
                .orElseThrow(() -> new DataNotFoundException("Url not found"));
        return urlEntity.getVisits();
    }

    @Override
    public String redirect(String shortUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new DataNotFoundException("Rout not found"));
        return urlEntity.getUrl();
    }
}
