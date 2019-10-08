package com.tokegenerator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tokegenerator.domain.Token;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {

    private String value;
    private String message;
    private boolean isValid;

    public TokenDTO(Token token) {
        this.value = token.getValue();
        setIsValid(token.getExpirationDateTime());
        setMessage();
    }

    private void setIsValid(LocalDateTime localDateTime) {
        this.isValid = LocalDateTime.now().isBefore(localDateTime);
    }

    private void setMessage() {
        if (this.isValid)
            this.message = "Your token is successfully validated.";
        else
            this.message = "This token is expired.";
    }
}
