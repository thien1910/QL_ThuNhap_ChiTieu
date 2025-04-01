package com.example.Ql_ThuNhap.Dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalIncomeResponse {
    private Long userId;
    private Long totalIncome;
}
