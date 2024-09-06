package com.pragma.emazon.users_microservice.domain.port.spi;

public interface IBCryptPasswordPort {

    public String encryptPassword(String password);
}
