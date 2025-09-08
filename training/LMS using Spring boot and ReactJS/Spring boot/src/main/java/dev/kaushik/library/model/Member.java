package dev.kaushik.library.model;

import java.time.LocalDateTime;

import dev.kaushik.library.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"createdAt","createdBy","updatedAt","updatedBy"})
@ToString(exclude = {"createdAt","createdBy","updatedAt","updatedBy"})
public class Member {
	private Integer memberID;

	private String name;

	private String email;

	private long phoneNumber;

	@NotNull(message = "Gender cannot be null")
	private Gender gender;

	private String address;
	
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime updatedAt;
	private String updatedBy;
}