package com.example.Ql_ThuNhap.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "savings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TietKiem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long tietKienId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;
    Long soTien;
    Long mucTieu;
    LocalDate ngayGuiTietKiem;
    LocalDate ngayDatMucTieu;
    String ghiChu;
}
