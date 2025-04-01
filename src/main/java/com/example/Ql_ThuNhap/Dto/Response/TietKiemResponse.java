package com.example.Ql_ThuNhap.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TietKiemResponse {
    Long tietKienId;
    Long userId;
    Long soTien;
    Long mucTieu;
    LocalDate ngayGuiTietKiem;
    LocalDate ngayDatMucTieu;
    String ghiChu;
    String message;
    Timestamp createdAt;
    Timestamp updatedAt;
}
