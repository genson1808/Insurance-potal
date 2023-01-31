package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.User;


public interface IUserService extends IAbstractService<User> {

    Boolean isExistUser(String username, String phoneNumber, String email);

    //User update(RequestUpdateUser user, Long id);

    User findByUsername(String username);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    //User findUserByRefreshToken(RefreshToken refreshToken);

    //ResponseUserDetail userToUserResponse(User user);

    User findUserByPhoneNumber(String phoneNumber);
}
