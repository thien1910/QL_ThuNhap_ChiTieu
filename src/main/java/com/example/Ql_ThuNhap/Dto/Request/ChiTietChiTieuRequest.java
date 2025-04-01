package com.example.Ql_ThuNhap.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietChiTieuRequest {
    Long chiTieuId;
    String tenKhoanChiNho;
    Long soTienKhoanChi;
    String ghiChuKhoanChiNho;
}
