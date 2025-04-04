package com.example.Ql_ThuNhap.Controller;


import com.example.Ql_ThuNhap.Dto.Request.ApiResponse;
import com.example.Ql_ThuNhap.Dto.Request.ThuNhapRequest;
import com.example.Ql_ThuNhap.Dto.Response.ThuNhapResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalIncomeResponse;
import com.example.Ql_ThuNhap.Dto.Response.UserFinancialSummaryResponse;
import com.example.Ql_ThuNhap.Dto.Update.ThuNhapUpdateRequest;
import com.example.Ql_ThuNhap.Service.ThuNhapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thu-nhap")
@RequiredArgsConstructor
public class ThuNhapController {
    @Autowired
    private ThuNhapService thuNhapService;

    @PostMapping("/add")
    ApiResponse<ThuNhapResponse> createThuNhap(@RequestBody @Valid ThuNhapRequest thuNhapRequest) {
        return ApiResponse.<ThuNhapResponse>builder()
                .result(thuNhapService.createThuNhap(thuNhapRequest))
                .build();
    }


    @GetMapping("/list")
    ApiResponse<List<ThuNhapResponse>> getAllThuNhap() {
        return ApiResponse.<List<ThuNhapResponse>>builder()
                .result(thuNhapService.getAllThuNhap())
                .build();
    }


    @GetMapping("/{thuNhapId}")
    ApiResponse<ThuNhapResponse> getThuNhap(@PathVariable("thuNhapId") Long thuNhapId) {
        return ApiResponse.<ThuNhapResponse>builder()
                .result(thuNhapService.getThuNhap(thuNhapId))
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<ThuNhapResponse>> getThuNhapByUserId(@PathVariable Long userId) {
        return ApiResponse.<List<ThuNhapResponse>>builder()
                .result(thuNhapService.getThuNhapByUserId(userId))
                .build();
    }
    @GetMapping("/total-income/{userId}")
    ApiResponse<TotalIncomeResponse> getTotalIncome(@PathVariable Long userId) {
        return ApiResponse.<TotalIncomeResponse>builder()
                .result(thuNhapService.getTotalIncomeByUserId(userId))
                .build();
    }


    @PutMapping("/{thuNhapId}")
    ApiResponse<ThuNhapResponse> updateThuNhap(
            @PathVariable Long thuNhapId,
            @RequestBody @Valid ThuNhapUpdateRequest request) {
        return ApiResponse.<ThuNhapResponse>builder()
                .result(thuNhapService.updateThuNhap(thuNhapId, request))
                .build();
    }

    @DeleteMapping("/{thuNhapId}")
    ApiResponse<String> deleteThuNhap(@PathVariable Long thuNhapId){
        thuNhapService.deleteThuNhap(thuNhapId);
        return ApiResponse.<String>builder()
                .result("Thu nhập đã được xóa")
                .build();
    }
    // lấy ra so du, tong thu nhap, chi tieu, tiet kiem
    @GetMapping("/total-summary/{userId}")
    public ApiResponse<UserFinancialSummaryResponse> getUserFinancialSummary(@PathVariable Long userId) {
        return ApiResponse.<UserFinancialSummaryResponse>builder()
                .result(thuNhapService.getUserFinancialSummary(userId))
                .build();
    }
    @GetMapping("/total-summary-by-date/{userId}")
    public ApiResponse<UserFinancialSummaryResponse> getUserFinancialSummaryByDate(
            @PathVariable Long userId,
            @RequestParam int month,
            @RequestParam int year) {
        return ApiResponse.<UserFinancialSummaryResponse>builder()
                .result(thuNhapService.getUserFinancialSummaryByMonthYear(userId, month, year))
                .build();
    }
}
