package com.tokegenerator.repository;

import com.tokegenerator.domain.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {

    Token findByValue(String value);
}
