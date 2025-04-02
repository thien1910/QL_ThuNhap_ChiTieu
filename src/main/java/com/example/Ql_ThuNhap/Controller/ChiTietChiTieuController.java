package com.example.Ql_ThuNhap.Controller;

import com.example.Ql_ThuNhap.Dto.Request.ApiResponse;
import com.example.Ql_ThuNhap.Dto.Request.ChiTietChiTieuRequest;
import com.example.Ql_ThuNhap.Dto.Request.UserCreationRequest;
import com.example.Ql_ThuNhap.Dto.Response.ChiTietChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Response.ChiTietChiTieuTotalResponse;
import com.example.Ql_ThuNhap.Dto.Response.UserResponse;
import com.example.Ql_ThuNhap.Dto.Update.ChiTietChiTieuUpdateRequest;
import com.example.Ql_ThuNhap.Service.ChiTietChiTieuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chi-tiet-chi-tieu")
@RequiredArgsConstructor
public class ChiTietChiTieuController {
    @Autowired
    private ChiTietChiTieuService chiTietChiTieuService;

    @PostMapping("/add")
    ApiResponse<ChiTietChiTieuResponse> createChiTietChiTieu(@RequestBody @Valid ChiTietChiTieuRequest chiTietChiTieuRequest){
        return ApiResponse.<ChiTietChiTieuResponse>builder()
                .result(chiTietChiTieuService.createChiTietChiTieu(chiTietChiTieuRequest))
                .build();
    }

    @GetMapping("/{chiTietChiTieuId}")
    ApiResponse<ChiTietChiTieuResponse> getChiTietChiTieu(@PathVariable Long chiTietChiTieuId) {
        return ApiResponse.<ChiTietChiTieuResponse>builder()
                .result(chiTietChiTieuService.getChiTietChiTieu(chiTietChiTieuId))
                .build();
    }


    @GetMapping("/chiTieu/{chiTieuId}")
    public ApiResponse<List<ChiTietChiTieuResponse>> getChiTietChiTieuByChiTieuId(@PathVariable Long chiTieuId) {
        return ApiResponse.<List<ChiTietChiTieuResponse>>builder()
                .result(chiTietChiTieuService.getChiTietChiTieuByChiTieuId(chiTieuId))
                .build();
    }
    @GetMapping("/tong-so-tien/{chiTieuId}")
    public ResponseEntity<ChiTietChiTieuTotalResponse> getTongSoTien(@PathVariable Long chiTieuId) {
        Long tongSoTien = chiTietChiTieuService.getTongSoTienChiTietByChiTieuId(chiTieuId);
        // Tạo response với chiTieuId và tổng số tiền
        ChiTietChiTieuTotalResponse response = ChiTietChiTieuTotalResponse.builder()
                .chiTieuId(chiTieuId)
                .tongSoTienKhoanChi(tongSoTien)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{chiTietChiTieuId}")
    ApiResponse<ChiTietChiTieuResponse> updateChiTietChiTieu(
            @PathVariable Long chiTietChiTieuId,
            @RequestBody @Valid ChiTietChiTieuUpdateRequest chiTietChiTieuRequest) {
        return ApiResponse.<ChiTietChiTieuResponse>builder()
                .result(chiTietChiTieuService.updateChiTietChiTieu(chiTietChiTieuId, chiTietChiTieuRequest))
                .build();
    }

    @DeleteMapping("/{chiTietChiTieuId}")
    public ResponseEntity<Void> deleteChiTietChiTieu(@PathVariable Long chiTietChiTieuId) {
        chiTietChiTieuService.deleteChiTietChiTieu(chiTietChiTieuId);
        return ResponseEntity.noContent().build();
    }
}