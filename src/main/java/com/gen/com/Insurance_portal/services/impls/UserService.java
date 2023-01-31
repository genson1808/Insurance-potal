package com.gen.com.Insurance_portal.services.impls;

import com.gen.com.Insurance_portal.entites.User;
import com.gen.com.Insurance_portal.repositories.UserRepository;
import com.gen.com.Insurance_portal.services.IUserService;
import org.springframework.stereotype.Service;


@Service
public class UserService extends AbstractService<User> implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Boolean isExistUser(String username, String phoneNumber, String email) {
        return userRepository.existsByUsernameOrPhoneNumberOrEmail(username, phoneNumber, email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return false;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }
}
