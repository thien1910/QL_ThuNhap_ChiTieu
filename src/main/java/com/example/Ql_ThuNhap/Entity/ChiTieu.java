package com.example.Ql_ThuNhap.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTieu extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chiTieuId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;
    Long soTien; // Số tiền chi tiêu
    String loaiChiTieu; // Loại chi tiêu (ăn uống, đi lại,...)
    LocalDate ngayChiTieu; // Ngày chi tiêu
    String ghiChu; // Ghi chú
}
