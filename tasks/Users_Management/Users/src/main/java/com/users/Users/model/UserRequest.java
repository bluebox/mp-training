package com.users.Users.model;

import com.users.Users.enums.RequestStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest extends User{
    private Integer requestId;
    private RequestStatus aprovedStatus;
}
