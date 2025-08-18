package model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Member {

    private int Id;

    @NotBlank(message = "Name cannot be empty")
    private String Name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String Email;

    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String Mobile;

    @NotNull(message = "Gender must be selected")
    private Gender gender;

    @NotBlank(message = "Address cannot be empty")
    private String Address;
}

