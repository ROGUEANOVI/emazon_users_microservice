package com.pragma.emazon.users_microservice.domain.port.spi;

public interface IBCryptPasswordSecurityPort {

    public String encryptPassword(String password);
}
