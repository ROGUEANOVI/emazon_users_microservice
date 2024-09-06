package com.pragma.emazon.users_microservice.infrastructure.out.jpa.service;

import com.pragma.emazon.users_microservice.domain.constant.UserExceptionMessages;
import com.pragma.emazon.users_microservice.domain.exception.UserNotFoundException;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                                        new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND, email));

        List<SimpleGrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(userEntity.getRole().getName()));

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorityList);
    }
}
