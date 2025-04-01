package com.example.Ql_ThuNhap.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThuNhap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long thuNhapId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;
    Long soTien; // Số tiền thu nhập
    String nguonThuNhap;
    LocalDate ngayNhan; // Ngày nhận
    String ghiChu; // Ghi chú
}
