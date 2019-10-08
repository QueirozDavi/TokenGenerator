package com.tokegenerator.controller.v1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokegenerator.domain.dto.TokenDTO;
import com.tokegenerator.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/token")
@CrossOrigin(origins = "*")
public class TokenController {

    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TokenController(TokenService tokenService, ObjectMapper objectMapper) {
        this.tokenService = tokenService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public JsonNode generateToken() {
        Map<String, String> map = tokenService.generateToken();
        ObjectNode response = objectMapper.convertValue(map, ObjectNode.class);

        return response.get("token_value");
    }

    @GetMapping("/information")
    public TokenDTO getTokenInformations(@RequestParam(value = "token", required = true, defaultValue = "") String token) {
        return tokenService.getTokenInformations(token);
    }
}
