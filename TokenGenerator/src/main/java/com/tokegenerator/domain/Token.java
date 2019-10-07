package com.tokegenerator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document
public class Token extends DocumentBase {

    @Id
    private String id;
    private String value;
    private LocalDateTime expirationDateTime;

    public Token(String value, LocalDateTime expirationDateTime) {
        this.value = value;
        this.expirationDateTime = expirationDateTime;
    }
}
