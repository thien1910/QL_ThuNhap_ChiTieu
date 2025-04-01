package com.example.Ql_ThuNhap.Controller;


import com.example.Ql_ThuNhap.Dto.Request.ApiResponse;
import com.example.Ql_ThuNhap.Dto.Request.ThuNhapRequest;
import com.example.Ql_ThuNhap.Dto.Response.ThuNhapResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalIncomeResponse;
import com.example.Ql_ThuNhap.Dto.Update.ThuNhapUpdateRequest;
import com.example.Ql_ThuNhap.Service.ThuNhapService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thu-nhap")
@RequiredArgsConstructor
public class ThuNhapController {
    @Autowired
    private ThuNhapService thuNhapService;

    @PostMapping("/add")
    public ResponseEntity<ThuNhapResponse> createThuNhap(@RequestBody ThuNhapRequest thuNhapRequest) {
        return ResponseEntity.ok(thuNhapService.createThuNhap(thuNhapRequest));
    }

    @GetMapping("/list")
    public List<ThuNhapResponse> getAllThuNhap() {
        return thuNhapService.getAllThuNhap();
    }

    @GetMapping("/{thuNhapId}")
    public ThuNhapResponse getThuNhap(
            @PathVariable("thuNhapId") Long thuNhapId) {
        return thuNhapService.getThuNhap(thuNhapId);
    }
    @GetMapping("/user/{userId}")
    public ApiResponse<List<ThuNhapResponse>> getThuNhapByUserId(@PathVariable Long userId) {
        return ApiResponse.<List<ThuNhapResponse>>builder()
                .result(thuNhapService.getThuNhapByUserId(userId))
                .build();
    }
    @GetMapping("/total-income/{userId}")
    public ResponseEntity<TotalIncomeResponse> getTotalIncome(@PathVariable Long userId) {
        return ResponseEntity.ok(thuNhapService.getTotalIncomeByUserId(userId));
    }

    @PutMapping("/{thuNhapId}")
    public ResponseEntity<ThuNhapResponse> updateThuNhap(
            @PathVariable Long thuNhapId,
            @RequestBody ThuNhapUpdateRequest request) {
        return ResponseEntity.ok(thuNhapService.updateThuNhap(thuNhapId, request));
    }
    @DeleteMapping("/{thuNhapId}")
    ApiResponse<String> deleteThuNhap(@PathVariable Long thuNhapId){
        thuNhapService.deleteThuNhap(thuNhapId);
        return ApiResponse.<String>builder()
                .result("Thu nhập đã được xóa")
                .build();
    }
}
