package com.medplus.Roles_backend.domain;

import com.medplus.Roles_backend.enums.ActiveStatus;

import lombok.Data;

@Data
public class RoleResponse extends RoleLocation{
    private ActiveStatus status;
}
