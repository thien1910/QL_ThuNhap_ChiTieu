package com.example.Ql_ThuNhap.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    Long userId;
    String fullName;
    String email;
    LocalDate dateOfBirth;
    Long soDu;
    boolean authenticated;
}