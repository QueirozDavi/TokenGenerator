package com.tokegenerator.repository;

import com.tokegenerator.domain.Token;
import com.tokegenerator.domain.TokenStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TokenRepository extends MongoRepository<Token, String> {

    Token findByValue(String value);

    List<Token> findByStatusNotAndExpirationDateTimeLessThan(TokenStatusEnum statusEnum,
                                                             LocalDateTime expirationDateTime);
}
