package com.pragma.emazon.users_microservice.infrastructure.out.security.adapter;

import com.pragma.emazon.users_microservice.domain.port.spi.IBCryptPasswordSecurityPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordSecurityAdapter implements IBCryptPasswordSecurityPort {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordSecurityAdapter() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encryptPassword(String password) {

        return bCryptPasswordEncoder.encode(password);
    }
}
