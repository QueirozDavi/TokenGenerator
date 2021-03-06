package com.tokegenerator.service.jobs;

import com.tokegenerator.domain.Token;
import com.tokegenerator.domain.TokenStatusEnum;
import com.tokegenerator.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenJob {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenJob(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(cron = "* 0/30 * * * *")
    @Transactional
    public void deleteExpiredTokens() {
        List<Token> expiredTokens = tokenRepository.findByStatusNotAndExpirationDateTimeLessThan(
                TokenStatusEnum.UNVALIDATED, LocalDateTime.now());

        if(!CollectionUtils.isEmpty(expiredTokens)){
            tokenRepository.deleteAll(expiredTokens.stream()
                    .filter(t -> t.getExpirationDateTime().isBefore(LocalDateTime.now())).collect(Collectors.toList()));
        }

    }
}
