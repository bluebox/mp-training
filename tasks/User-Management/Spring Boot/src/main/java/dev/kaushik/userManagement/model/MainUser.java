package dev.kaushik.userManagement.model;

import dev.kaushik.userManagement.model.enums.Status;
import lombok.*;

@Getter
@Setter
@ToString
public class MainUser extends User {
	private Status status = Status.ACTIVE;
	private String userName;
	private String password;
}
