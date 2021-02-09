package com.poseidon.app.persistence.mapper;

import com.poseidon.app.config.MapperConfig;
import com.poseidon.app.model.User;
import com.poseidon.app.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Scope;

@Mapper(config = MapperConfig.class)
@Scope("singleton")
public interface UserMapper {
    User toUser(UserEntity userEntity);

    UserEntity fromUser(User userEntity);
}
