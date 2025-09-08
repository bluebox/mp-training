package com.medplus.Roles_backend.domain;

import lombok.Data;
import java.util.List;

@Data
public class ActiveMembers extends BaseUser {
    private String userId;
    private String password;

    private List<String> roles;
}
