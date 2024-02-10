package com.example.shortener.repository;

import com.example.shortener.entity.UrlEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, ObjectId> {
    Optional<UrlEntity> findByShortUrlAndCode(String shortUrl, String code);
    Optional<UrlEntity> findByShortUrl(String shortUrl);
}
