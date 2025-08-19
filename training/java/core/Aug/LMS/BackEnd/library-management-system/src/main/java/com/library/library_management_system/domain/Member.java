package com.library.library_management_system.domain;

import java.util.Objects;

import com.library.library_management_system.utils.MemberGender;
import com.library.library_management_system.utils.MemberStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {

	private int id;

	@NotBlank(message = "Name cannot be Empty")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 length")
	@Pattern(regexp = "[a-zA-Z -.]{0,50}", message = "Name only contain aplhabates -.")
	private String name;

	@NotBlank(message = "Email is required")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email!")
	private String email;

	@Min(value = 6000000000L, message = "Mobile must be exactly 10 digits")
	@Max(value = 9999999999L, message = "Mobile must be exactly 10 digits")
	@NotNull(message = "Mobile is required")
	private Long mobile;

	@NotNull(message = "Gender is required")
	private MemberGender gender;

	@NotBlank(message = "Address cannot be Empty")
	@Size(min = 3, max = 50, message = "Address must be between 3 and 50 length")
	@Pattern(regexp = "^[A-Za-z0-9\\s,./#-]{0,100}$", message = "Address only contain aplhabates,Numbers /#,-.")
	private String address;

	private MemberStatus status;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Member other = (Member) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && Objects.equals(mobile, other.mobile)
				&& Objects.equals(name, other.name) && Objects.equals(status, other.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, gender, id, mobile, name, status);
	}

}
