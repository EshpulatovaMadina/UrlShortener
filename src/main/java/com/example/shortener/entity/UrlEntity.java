package com.example.shortener.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(value = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlEntity {
    @Id
    private ObjectId id;
    private String url;
    @Indexed(unique = true)
    private String shortUrl;
    private String code;
    private List<Visits> visits;

}
