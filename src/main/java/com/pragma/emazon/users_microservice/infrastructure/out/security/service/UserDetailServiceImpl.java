package com.pragma.emazon.users_microservice.infrastructure.out.security.service;

import com.pragma.emazon.users_microservice.domain.constant.AuthValidationMessages;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.emazon.users_microservice.infrastructure.out.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                                        new BadCredentialsException(AuthValidationMessages.INVALID_CREDENTIALS));

        List<SimpleGrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(userEntity.getRole().getName()));

        return new CustomUserDetails( userEntity.getId(),userEntity.getEmail(), userEntity.getPassword(), authorityList);
    }

    public Authentication authenticate(String email, String password) {

        UserDetails userDetails = loadUserByUsername(email);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(AuthValidationMessages.INVALID_CREDENTIALS);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
