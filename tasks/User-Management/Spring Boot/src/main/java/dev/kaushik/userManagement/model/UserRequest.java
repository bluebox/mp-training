package dev.kaushik.userManagement.model;

import dev.kaushik.userManagement.model.enums.Approval;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends User {
	private int requestId;
	private Approval approval = Approval.PENDING;
}
