package com.gember.identity_service.configuration;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtEncoder implements JwtEncoder {

    private String signerKey;

    @Override
    public Jwt encode(JwtEncoderParameters parameters) throws JwtEncodingException {
        return null;
    }
}