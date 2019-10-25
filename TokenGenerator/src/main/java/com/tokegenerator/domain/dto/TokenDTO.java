package com.tokegenerator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tokegenerator.domain.Token;
import com.tokegenerator.domain.TokenStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {

    private String id;
    private String value;
    private String message;
    private boolean isValid;

    public TokenDTO(Token token) {
        this.id = token.getId();
        this.value = token.getValue();
        setIsValid(token.getExpirationDateTime(), token.getStatus());
        setMessage();
    }

    private void setIsValid(LocalDateTime localDateTime, TokenStatusEnum statusEnum) {
        if (statusEnum.equals(TokenStatusEnum.UNVALIDATED))
            this.isValid = LocalDateTime.now().isBefore(localDateTime);
        else
            this.isValid = false;
    }

    private void setMessage() {
        if (this.isValid)
            this.message = "Your token is successfully validated.";
        else
            this.message = "This token is expired.";
    }
}
