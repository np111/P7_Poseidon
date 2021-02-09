package com.poseidon.app.service;

import com.poseidon.app.model.User;
import com.poseidon.app.persistence.entity.UserEntity;
import com.poseidon.app.persistence.mapper.UserMapper;
import com.poseidon.app.persistence.repository.UserRepository;
import java.util.Collections;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Scope("singleton")
public class UserService extends CrudService<User, UserEntity, Long> implements UserDetailsService {
    private final @Getter UserRepository repository;
    private final @Getter UserMapper mapper;

    @Override
    protected void setModelId(User user, Long id) {
        user.setId(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
