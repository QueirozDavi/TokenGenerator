package com.tokegenerator.service;

import com.tokegenerator.domain.dto.TokenDTO;
import com.tokegenerator.domain.Token;
import com.tokegenerator.exception.NotFoundException;
import com.tokegenerator.repository.TokenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TokenService {

    private TokenRepository tokenRepository;
    private final int STRING_SIZE = 3;
    private final int DURATION = 10;


    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String generateToken() {

        Token token = new Token(RandomStringUtils.random(STRING_SIZE).toUpperCase(),
                LocalDateTime.now().plusMinutes(DURATION));
        tokenRepository.save(token);

        return token.getValue();
    }

    public TokenDTO getTokenInformations(String tokenValue) {

        Token token = tokenRepository.findByValue(tokenValue);
        if (Objects.nonNull(token))
            return new TokenDTO(token);
        else
            throw new NotFoundException("Token Not Found");

    }
}
