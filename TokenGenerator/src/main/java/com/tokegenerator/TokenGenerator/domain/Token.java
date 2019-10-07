package com.tokegenerator.TokenGenerator.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Token extends DocumentBase{

    @Id
    private String id;

    private LocalDateTime expirationDateTime;

}
