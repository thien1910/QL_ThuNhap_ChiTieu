package com.example.Ql_ThuNhap.Controller;

import com.example.Ql_ThuNhap.Dto.Request.ApiResponse;
import com.example.Ql_ThuNhap.Dto.Request.ChiTieuRequest;
import com.example.Ql_ThuNhap.Dto.Response.ChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalExpenseResponse;
import com.example.Ql_ThuNhap.Dto.Update.ChiTieuUpdateRequest;
import com.example.Ql_ThuNhap.Service.ChiTieuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chi-tieu")
@RequiredArgsConstructor
public class ChiTieuControlller {
    @Autowired
    private ChiTieuService chiTieuService;

    @PostMapping("/add")
    ApiResponse<ChiTieuResponse> createChiTieu(@RequestBody @Valid ChiTieuRequest chiTieuRequest) {
        return ApiResponse.<ChiTieuResponse>builder()
                .result(chiTieuService.createChiTieu(chiTieuRequest))
                .build();
    }


    @GetMapping("/list")
    ApiResponse<List<ChiTieuResponse>> getAllChiTieu() {
        return ApiResponse.<List<ChiTieuResponse>>builder()
                .result(chiTieuService.getAllChiTieu())
                .build();
    }


    @GetMapping("/{chiTieuId}")
    ApiResponse<ChiTieuResponse> getThuNhap(@PathVariable("chiTieuId") Long chiTieuId) {
        return ApiResponse.<ChiTieuResponse>builder()
                .result(chiTieuService.getChiTieu(chiTieuId))
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<ChiTieuResponse>> getChiTieuByUserId(@PathVariable Long userId) {
        return ApiResponse.<List<ChiTieuResponse>>builder()
                .result(chiTieuService.getChiTieuByUserId(userId))
                .build();
    }
    @GetMapping("/total-expense/{userId}")
    ApiResponse<TotalExpenseResponse> getTotalExpense(@PathVariable Long userId) {
        return ApiResponse.<TotalExpenseResponse>builder()
                .result(chiTieuService.getTotalExpenseByUserId(userId))
                .build();
    }


    @PutMapping("/{chiTieuId}")
    ApiResponse<ChiTieuResponse> updateChiTieu(
            @PathVariable Long chiTieuId,
            @RequestBody ChiTieuUpdateRequest chiTieuRequest){
        return ApiResponse.<ChiTieuResponse>builder()
                .result(chiTieuService.updateChiTieu(chiTieuId, chiTieuRequest))
                .build();
    }

    @DeleteMapping("/{chiTieuId}")
    ApiResponse<String> deleteChiTieu(@PathVariable Long chiTieuId){
        chiTieuService.deleteChiTieu(chiTieuId);
        return ApiResponse.<String>builder()
                .result("Chi tiêu đã được xóa")
                .build();
    }
}
