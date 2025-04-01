package com.example.Ql_ThuNhap.Dto.Update;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor // khoi tao ko tham so
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTieuUpdateRequest {
    Long soTien; // Số tiền chi tiêu
    String loaiChiTieu; // Loại chi tiêu (ăn uống, đi lại,...)
    LocalDate ngayChiTieu; // Ngày chi tiêu
    String ghiChu; // Ghi chú
}
