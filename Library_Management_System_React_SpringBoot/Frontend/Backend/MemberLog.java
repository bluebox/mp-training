package com.library.Library.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "members_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    private Integer memberId;
    private String name;
    private String email;
    private Long mobile;  
    private String gender;
    private String address;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
