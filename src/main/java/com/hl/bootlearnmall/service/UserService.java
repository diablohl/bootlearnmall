package com.hl.bootlearnmall.service;

import com.hl.bootlearnmall.domain.User;

public interface UserService {
    User getUser();
    void register(String username,String password);
}
