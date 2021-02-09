package com.poseidon.app.service;

import com.poseidon.app.model.User;
import com.poseidon.app.persistence.entity.UserEntity;
import com.poseidon.app.persistence.mapper.UserMapper;
import com.poseidon.app.persistence.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Scope("singleton")
public class UserService extends CrudService<User, UserEntity, Long> {
    private final @Getter UserRepository repository;
    private final @Getter UserMapper mapper;

    @Override
    protected void setModelId(User user, Long id) {
        user.setId(id);
    }
}
