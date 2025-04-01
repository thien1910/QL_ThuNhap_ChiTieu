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
public class TietKiemRequest {
    Long userId;
    Long soTien;
    Long mucTieu;
    LocalDate ngayGuiTietKiem;
    LocalDate ngayDatMucTieu;
    String ghiChu;
}
