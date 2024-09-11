package com.pragma.emazon.users_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.users_microservice.domain.model.User;
import com.pragma.emazon.users_microservice.domain.port.spi.IUserPersistencePort;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IUserRepository;
import org.springframework.transaction.annotation.Transactional;

public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    public UserJpaAdapter(IUserRepository userRepository, UserEntityMapper userEntityMapper) {

        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    @Transactional
    public void createUser(User user) {

        userRepository.save(userEntityMapper.toUserEntity(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsUserByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsUserByDocumentId(String documentId) {

        return userRepository.existsByDocumentId(documentId);
    }
}
