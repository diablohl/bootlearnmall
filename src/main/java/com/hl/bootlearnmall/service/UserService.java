package com.hl.bootlearnmall.service;

import com.hl.bootlearnmall.domain.User;

public interface UserService {
    User getUser();
    void register(String username,String password);

    User login(String username, String password);

    void updateUserInformation(User user);

    boolean checkAdminRole(User user);
}
