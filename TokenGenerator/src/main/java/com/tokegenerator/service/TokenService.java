package com.tokegenerator.service;

import com.tokegenerator.domain.TokenStatusEnum;
import com.tokegenerator.domain.dto.TokenDTO;
import com.tokegenerator.domain.Token;
import com.tokegenerator.exception.NotFoundException;
import com.tokegenerator.repository.TokenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class TokenService {

    private TokenRepository tokenRepository;
    private final int STRING_SIZE = 5;
    private final int DURATION = 10;
    private EmailService emailService;
    private SimpleMailMessage template;


    @Autowired
    public TokenService(TokenRepository tokenRepository, JavaMailSender emailSender, EmailService emailService,
                        SimpleMailMessage template) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.template = template;
    }

    public Map<String, String> generateToken(@Email String email) {

        Token token = new Token(RandomStringUtils.randomAlphabetic(STRING_SIZE).toUpperCase(),
                LocalDateTime.now().plusMinutes(DURATION));
        tokenRepository.save(token);

        if(Objects.nonNull(email))
            emailService.sendTokenByEmail(email, "Here is your token", getEmailText(token.getValue()));

        Map<String, String> response = new HashMap<>();

        response.put("token_value", token.getValue());

        return response;
    }

    private String getEmailText(String token) {

        return String.format(Objects.requireNonNull(template.getText()), token);
    }

    public TokenDTO getTokenInformation(String tokenValue) {

        Token token = tokenRepository.findByValue(tokenValue);
        TokenDTO tokenDTO;

        if (Objects.nonNull(token)) {
            tokenDTO = new TokenDTO(token);
            token.setStatus(TokenStatusEnum.VALIDATED);
            tokenRepository.save(token);

            return tokenDTO;
        } else {
            throw new NotFoundException("Token Not Found");
        }
    }
}
