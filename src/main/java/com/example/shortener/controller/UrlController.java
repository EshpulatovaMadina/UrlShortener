package com.example.shortener.controller;

import com.example.shortener.DTO.request.UrlShortenDto;
import com.example.shortener.DTO.response.ShortenResponse;
import com.example.shortener.entity.Visits;
import com.example.shortener.service.UrlService;

import com.sun.net.httpserver.Headers;
import jakarta.servlet.ServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpClient;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    @PostMapping("/url/shorten")
    public ResponseEntity<ShortenResponse> shorten(@RequestBody UrlShortenDto urlShorten) {
        return ResponseEntity.ok(urlService.shorten(urlShorten));
    }

    @GetMapping("/url/info")
    public ResponseEntity<List<Visits>> getInfo(@RequestParam String shortUrl, @RequestParam String code) {
        return ResponseEntity.ok(urlService.getInfo(shortUrl, code));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(
            @PathVariable String shortUrl,
            ServletRequest servletRequest
    ) {
            String ipAddress = servletRequest.getRemoteAddr();
        System.out.println("IP Address: "+ipAddress);

        return new RedirectView(urlService.redirect(shortUrl,ipAddress));
    }

    @GetMapping("/")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("Server is working");
    }
}
