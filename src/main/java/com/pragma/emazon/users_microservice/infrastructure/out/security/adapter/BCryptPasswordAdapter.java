package com.pragma.emazon.users_microservice.infrastructure.out.security.adapter;

import com.pragma.emazon.users_microservice.domain.port.spi.IBCryptPasswordPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordAdapter implements IBCryptPasswordPort {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordAdapter() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encryptPassword(String password) {

        return bCryptPasswordEncoder.encode(password);
    }
}
