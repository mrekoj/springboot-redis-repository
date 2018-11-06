package com.skynet.sbredis;

import com.skynet.sbredis.config.RedisConfig;
import com.skynet.sbredis.model.User;
import com.skynet.sbredis.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser() {
        final User user = new User(1, "skynet");
        userRepository.add(user);

        final User userAfterAdd = userRepository.findUser(user.getId());
        Assert.assertEquals(user.getId(), userAfterAdd.getId());
    }

    @Test
    public void deleteUser() {
        final User user = new User(1, "skynet");
        userRepository.add(user);

        Map<Integer, User> allUser = userRepository.findAllUsers();
        int sizeBefore = allUser.size();

        allUser.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v.getId());
            System.out.println(v.getName());
        });

        userRepository.delete(user.getId());

        allUser = userRepository.findAllUsers();
        int sizeAfter = allUser.size();

        Assert.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
