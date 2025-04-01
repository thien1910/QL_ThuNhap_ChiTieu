package com.example.Ql_ThuNhap.Dto.Update;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor // khoi tao ko tham so
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    Long userId;
    String fullName;
    String email;
    String passWord;
    LocalDate dateOfBirth;
}
