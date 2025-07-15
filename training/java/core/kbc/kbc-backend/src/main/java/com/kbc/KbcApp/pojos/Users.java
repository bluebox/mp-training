package com.kbc.KbcApp.pojos;

import java.time.LocalDateTime;

import com.kbc.KbcApp.enums.RoleEnum;
import com.kbc.KbcApp.enums.StatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Users {
	private Integer userId;

	private String username;

	private String password;

	private RoleEnum role;

	private StatusEnum status;

	private LocalDateTime modifiedAt;
	private LocalDateTime createdAt;

	private String modifiedBy;


	public Users(String userName,String password,RoleEnum Role,StatusEnum status)
	{
		this.username=userName;
		this.password=password;
		this.role=Role;
		this.status=status;
	}
	public Users(String UserName,String password)
	{
		this.username=UserName;
		this.password=password;
	}
}
