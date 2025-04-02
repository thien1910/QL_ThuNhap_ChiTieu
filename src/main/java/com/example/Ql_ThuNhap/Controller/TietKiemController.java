package com.example.Ql_ThuNhap.Controller;

import com.example.Ql_ThuNhap.Dto.Request.ApiResponse;
import com.example.Ql_ThuNhap.Dto.Request.TietKiemRequest;
import com.example.Ql_ThuNhap.Dto.Response.TietKiemResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalSavingResponse;
import com.example.Ql_ThuNhap.Dto.Update.TietKiemUpdateRequest;
import com.example.Ql_ThuNhap.Service.TietKiemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiet-kiem")
@RequiredArgsConstructor
public class TietKiemController {
    @Autowired
    private TietKiemService tietKiemService;

    @PostMapping("/add")
    ApiResponse<TietKiemResponse> createTietKiem(@RequestBody @Valid TietKiemRequest tietKiemRequest) {
        return ApiResponse.<TietKiemResponse>builder()
                .result(tietKiemService.createTietKiem(tietKiemRequest))
                .build();
    }


    @GetMapping("/list")
    ApiResponse<List<TietKiemResponse>> getAllTietKiem() {
        return ApiResponse.<List<TietKiemResponse>>builder()
                .result(tietKiemService.getAllTietKiem())
                .build();
    }


    @GetMapping("/{tietKiemId}")
    ApiResponse<TietKiemResponse> getTietKiem(@PathVariable("tietKiemId") Long tietKiemId) {
        return ApiResponse.<TietKiemResponse>builder()
                .result(tietKiemService.getTietKiem(tietKiemId))
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<TietKiemResponse>> getTietKiemByUserId(@PathVariable Long userId) {
        return ApiResponse.<List<TietKiemResponse>>builder()
                .result(tietKiemService.getTietKiemByUserId(userId))
                .build();
    }
    @GetMapping("/total-saving/{userId}")
    ApiResponse<TotalSavingResponse> getTotalSaving(@PathVariable Long userId) {
        return ApiResponse.<TotalSavingResponse>builder()
                .result(tietKiemService.getTotalSavingByUserId(userId))
                .build();
    }


    @PutMapping("/{tietKiemId}")
    ApiResponse<TietKiemResponse> updateTietKiem(
            @PathVariable Long tietKiemId,
            @RequestBody @Valid TietKiemUpdateRequest tietKiemRequest) {
        return ApiResponse.<TietKiemResponse>builder()
                .result(tietKiemService.updateTietKiem(tietKiemId, tietKiemRequest))
                .build();
    }


    @DeleteMapping("/{tietKiemId}")
    ApiResponse<String> deleteTietKiem(@PathVariable Long tietKiemId){
        tietKiemService.deleteTietKiem(tietKiemId);
        return ApiResponse.<String>builder()
                .result("Tiết kiệm đã được xóa")
                .build();
    }
}
