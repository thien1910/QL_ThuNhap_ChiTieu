package com.example.Ql_ThuNhap.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String fullName;
    String email;
    String passWord;
    LocalDate dateOfBirth;
    @Column(nullable = false)
    Long soDu = 0L; // Giá trị mặc định là 0
}
