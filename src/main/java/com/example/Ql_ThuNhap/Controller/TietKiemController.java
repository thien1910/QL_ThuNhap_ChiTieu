package com.example.Ql_ThuNhap.Controller;

import com.example.Ql_ThuNhap.Dto.Request.ApiResponse;
import com.example.Ql_ThuNhap.Dto.Request.TietKiemRequest;
import com.example.Ql_ThuNhap.Dto.Response.TietKiemResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalSavingResponse;
import com.example.Ql_ThuNhap.Dto.Update.TietKiemUpdateRequest;
import com.example.Ql_ThuNhap.Service.TietKiemService;
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
    public ResponseEntity<TietKiemResponse> createTietKiem(@RequestBody TietKiemRequest tietKiemRequest) {
        return ResponseEntity.ok(tietKiemService.createTietKiem(tietKiemRequest));
    }

    @GetMapping("/list")
    public List<TietKiemResponse> getAllTietKiem() {
        return tietKiemService.getAllTietKiem();
    }

    @GetMapping("/{tietKiemId}")
    public TietKiemResponse getTietKiem(
            @PathVariable("tietKiemId") Long tietKiemId) {
        return tietKiemService.getTietKiem(tietKiemId);
    }
    @GetMapping("/user/{userId}")
    public ApiResponse<List<TietKiemResponse>> getTietKiemByUserId(@PathVariable Long userId) {
        return ApiResponse.<List<TietKiemResponse>>builder()
                .result(tietKiemService.getTietKiemByUserId(userId))
                .build();
    }
    @GetMapping("/total-saving/{userId}")
    public ResponseEntity<TotalSavingResponse> getTotalSaving(@PathVariable Long userId) {
        return ResponseEntity.ok(tietKiemService.getTotalSavingByUserId(userId));
    }

    @PutMapping("/{tietKiemId}")
    public ResponseEntity<TietKiemResponse> updateTietKiem(
            @PathVariable Long tietKiemId,
            @RequestBody TietKiemUpdateRequest tietKiemRequest) {
        return ResponseEntity.ok(tietKiemService.updateTietKiem(tietKiemId, tietKiemRequest));
    }

    @DeleteMapping("/{tietKiemId}")
    ApiResponse<String> deleteTietKiem(@PathVariable Long tietKiemId){
        tietKiemService.deleteTietKiem(tietKiemId);
        return ApiResponse.<String>builder()
                .result("Tiết kiệm đã được xóa")
                .build();
    }
}
