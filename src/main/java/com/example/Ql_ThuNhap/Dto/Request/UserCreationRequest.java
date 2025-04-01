package com.example.Ql_ThuNhap.Dto.Request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor // khoi tao ko tham so
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    Long userId;
    String fullName;
    String email;
    String passWord;
    LocalDate dateOfBirth;
    @Column(nullable = false)
    Long soDu = 0L; // Giá trị mặc định là 0
}
