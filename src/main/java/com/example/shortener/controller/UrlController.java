package com.example.shortener.controller;

import com.example.shortener.DTO.request.DeleteRequestDto;
import com.example.shortener.DTO.request.UrlShortenDto;
import com.example.shortener.DTO.response.ShortenResponse;
import com.example.shortener.entity.Visits;
import com.example.shortener.service.UrlService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    @PostMapping("/api/shorten")
    public ResponseEntity<ShortenResponse> shorten(@RequestBody UrlShortenDto urlShorten) {
        return ResponseEntity.ok(urlService.shorten(urlShorten));
    }

    @GetMapping("/api/info")
    public ResponseEntity<List<Visits>> getInfo(@RequestParam String shortUrl, @RequestParam String code) {
        return ResponseEntity.ok(urlService.getInfo(shortUrl, code));
    }

    @PutMapping("/api/edit")
    public ResponseEntity<ShortenResponse> editUrl(@RequestBody ShortenResponse shortenResponse) {
        return ResponseEntity.ok(urlService.edit(shortenResponse));
    }

    @DeleteMapping("/api/delete")
    public ResponseEntity<String> delete(@RequestBody DeleteRequestDto deleteRequestDto) {
        return ResponseEntity.ok(urlService.delete(deleteRequestDto));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(
            @PathVariable String shortUrl,
            @RequestHeader("X-Forwarded-For") String ipAddress
    ) {

        return new RedirectView(urlService.redirect(shortUrl,ipAddress));
    }

}
