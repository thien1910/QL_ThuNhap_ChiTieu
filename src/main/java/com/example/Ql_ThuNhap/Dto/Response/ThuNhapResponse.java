package com.example.Ql_ThuNhap.Dto.Response;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThuNhapResponse {

    Long thuNhapId;
    Long userId;
    Long soTien;
    String nguonThuNhap;
    LocalDate ngayNhan;
    String ghiChu;
    Timestamp createdAt;
    Timestamp updatedAt;
}
