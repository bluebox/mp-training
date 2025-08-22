package com.library.Library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    @Digits(integer = 10, fraction = 0, message = "Mobile must be exactly 10 digits")
    private Long mobile;

    @NotBlank
    @Pattern(regexp = "[MF]", message = "Gender must be 'M' or 'F'")
    private String gender;

    @NotBlank
    private String address;
}
