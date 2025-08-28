package com.VIMS.VIMSBackend.Config;

import com.VIMS.VIMSBackend.Model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private Boolean status;
    private UserModel user;
}
