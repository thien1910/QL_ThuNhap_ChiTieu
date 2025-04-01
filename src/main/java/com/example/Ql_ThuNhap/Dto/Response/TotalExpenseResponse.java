package com.example.Ql_ThuNhap.Dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalExpenseResponse {
    private Long userId;
    private Long totalExpense;
}
