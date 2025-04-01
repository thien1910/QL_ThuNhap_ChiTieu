package com.example.Ql_ThuNhap.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThuNhapRequest {

    Long userId;
    Long soTien;
    String nguonThuNhap;
    LocalDate ngayNhan;
    String ghiChu;
}
