package com.medplus.Roles_backend.dao;

import com.medplus.Roles_backend.domain.User;

public interface UserDaoInterface {

    User findByUsername(String username);

    User findById(String id);

    int updatePassword(String id, String encryptedPassword);
}
