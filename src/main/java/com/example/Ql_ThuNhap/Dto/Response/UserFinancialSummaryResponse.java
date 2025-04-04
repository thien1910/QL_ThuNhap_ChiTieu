package com.example.Ql_ThuNhap.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFinancialSummaryResponse {
    Long userId;
    Long soDu;         // Số dư hiện tại
    Long totalIncome;  // Tổng thu nhập
    Long totalExpense; // Tổng chi tiêu
    Long totalSaving;  // Tổng tiết kiệm
}
