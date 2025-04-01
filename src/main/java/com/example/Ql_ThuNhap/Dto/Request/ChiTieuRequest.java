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
public class ChiTieuRequest {
    Long userId;
    Long soTien; // Số tiền chi tiêu
    String loaiChiTieu; // Loại chi tiêu (ăn uống, đi lại,...)
    LocalDate ngayChiTieu; // Ngày chi tiêu
    String ghiChu; // Ghi chú
}
