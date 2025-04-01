package com.example.Ql_ThuNhap.Dto.Response;

import com.example.Ql_ThuNhap.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTieuResponse {
    Long chiTieuId;
    Long userId;
    Long soTien; // Số tiền chi tiêu
    String loaiChiTieu; // Loại chi tiêu (ăn uống, đi lại,...)
    LocalDate ngayChiTieu; // Ngày chi tiêu
    String ghiChu; // Ghi chú
    Timestamp createdAt;
    Timestamp updatedAt;
}
