package com.issue.iaserver.service;

import com.issue.iaserver.entity.User;

public interface UserService {
    User findById(long id);
    int updateUser(User user);
    int deleteById(long id);
}
