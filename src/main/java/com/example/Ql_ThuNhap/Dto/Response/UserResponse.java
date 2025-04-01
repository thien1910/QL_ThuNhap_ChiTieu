package com.example.Ql_ThuNhap.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor // khoi tao ko tham so
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long userId;
    String fullName;
    String email;
    String passWord;
    LocalDate dateOfBirth;
    Long soDu;
    Timestamp createdAt;
    Timestamp updatedAt;
}
