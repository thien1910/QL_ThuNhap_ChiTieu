package com.example.Ql_ThuNhap.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietChiTieu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chiTietChiTieuId;
    @ManyToOne
    @JoinColumn(name = "chi_tieu_id", referencedColumnName = "chiTieuId")
    ChiTieu chiTieu;
    String tenKhoanChiNho;
    Long soTienKhoanChi;
    String ghiChuKhoanChiNho;
}
