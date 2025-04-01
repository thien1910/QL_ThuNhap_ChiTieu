package com.example.Ql_ThuNhap.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietChiTieuResponse {
    Long chiTietChiTieuId;
    Long chiTieuId;
    String tenKhoanChiNho;
    Long soTienKhoanChi;
    String ghiChuKhoanChiNho;
    Timestamp createdAt;
    Timestamp updatedAt;
}
