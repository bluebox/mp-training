package com.example.SpringBootDatabaseMember.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memberId;

	@Getter
	@Setter
	private String memberName;

	@Getter
	@Setter
	private String memberMail;

	@Getter
	@Setter
	private double memberMobileNo;

	@Getter
	@Setter
	private String memberGender;

	@Getter
	@Setter
	private String memberAddress;

}
