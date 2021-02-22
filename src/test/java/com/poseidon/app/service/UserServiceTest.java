package com.poseidon.app.service;

import com.poseidon.app.model.User;
import com.poseidon.app.persistence.entity.UserEntity;
import com.poseidon.app.util.PojoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest extends AbstractCrudServiceTest<UserService, User, UserEntity, Long> {
    public UserServiceTest() {
        super(UserService.class, User.class, UserEntity.class, Long.class);
    }

    @Test
    void loadUserByUsername() {
        service.getRepository().deleteAll();

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("test"));

        User excepted = PojoUtil.getPodam().manufacturePojoWithFullData(User.class);
        excepted.setUsername("test");
        excepted = service.create(PojoUtil.deepClone(excepted));

        UserDetails userDetails = service.loadUserByUsername("test");
        assertEquals(excepted.getUsername(), userDetails.getUsername());
        assertEquals(excepted.getPassword(), userDetails.getPassword());

        assertArrayEquals(
                new String[]{excepted.getRole().name()},
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray());
    }
}
