package com.example.Ql_ThuNhap.Dto.Update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor // khoi tao ko tham so
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietChiTieuUpdateRequest {
    String tenKhoanChiNho;
    Long soTienKhoanChi;
    String ghiChuKhoanChiNho;
}
