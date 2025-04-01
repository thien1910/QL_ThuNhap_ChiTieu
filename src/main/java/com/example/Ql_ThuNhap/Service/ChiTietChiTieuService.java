package com.example.Ql_ThuNhap.Service;

import com.example.Ql_ThuNhap.Dto.Request.ChiTietChiTieuRequest;
import com.example.Ql_ThuNhap.Dto.Response.ChiTietChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Response.ChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Update.ChiTietChiTieuUpdateRequest;
import com.example.Ql_ThuNhap.Entity.ChiTietChiTieu;
import com.example.Ql_ThuNhap.Entity.ChiTieu;
import com.example.Ql_ThuNhap.Exception.AppException;
import com.example.Ql_ThuNhap.Exception.ErrorCode;
import com.example.Ql_ThuNhap.Mapper.ChiTietChiTieuMapper;
import com.example.Ql_ThuNhap.Repository.ChiTietChiTieuRepository;
import com.example.Ql_ThuNhap.Repository.ChiTieuRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietChiTieuService {
    @Autowired
    ChiTietChiTieuRepository chiTietChiTieuRepository;
    @Autowired
    ChiTieuRepository chiTieuRepository;
    @Autowired
    ChiTietChiTieuMapper chiTietChiTieuMapper;

    // Tạo chi tiết chi tiêu
    public ChiTietChiTieuResponse createChiTietChiTieu(ChiTietChiTieuRequest chiTietChiTieuRequest) {
        ChiTieu chiTieu = chiTieuRepository.findById(chiTietChiTieuRequest.getChiTieuId())
                .orElseThrow(() -> new AppException(ErrorCode.CHI_TIEU_NOT_FOUND));

        // Tính tổng số tiền đã chi tiết
        List<ChiTietChiTieu> chiTietList = chiTietChiTieuRepository.findByChiTieu_chiTieuId(chiTieu.getChiTieuId());
        long tongSoTienChiTiet = chiTietList.stream().mapToLong(ChiTietChiTieu::getSoTienKhoanChi).sum();

        // Kiểm tra nếu tổng chi tiết + số tiền mới lớn hơn số tiền của ChiTieu
        if (tongSoTienChiTiet + chiTietChiTieuRequest.getSoTienKhoanChi() > chiTieu.getSoTien()) {
            throw new AppException(ErrorCode.SO_TIEN_VUOT_QUA_HAN_MUC);
        }

        ChiTietChiTieu chiTietChiTieu = chiTietChiTieuMapper.toChiTietChiTieu(chiTietChiTieuRequest, chiTieu);
        chiTietChiTieu = chiTietChiTieuRepository.save(chiTietChiTieu);

        return chiTietChiTieuMapper.toChiTietChiTieuResponse(chiTietChiTieu);
    }

    public ChiTietChiTieuResponse getChiTietChiTieu(Long chiTietChiTieuId) {
        return chiTietChiTieuRepository.findById(chiTietChiTieuId)
                .map(chiTietChiTieuMapper::toChiTietChiTieuResponse)
                .orElseThrow(() -> new RuntimeException("Chi tiêu không tồn tại"));
    }

    // Lấy danh sách chi tiết chi tiêu theo chi tiêu ID
    public List<ChiTietChiTieuResponse> getChiTietChiTieuByChiTieuId(Long chiTieuId){
        List<ChiTietChiTieu> chiTietChiTieuList = chiTietChiTieuRepository.findByChiTieu_chiTieuId(chiTieuId);
        return chiTietChiTieuList.stream()
                .map(chiTietChiTieuMapper::toChiTietChiTieuResponse)
                .collect(Collectors.toList());
    }

    // Cập nhật chi tiết chi tiêu
    public ChiTietChiTieuResponse updateChiTietChiTieu(Long chiTietChiTieuId, ChiTietChiTieuUpdateRequest chiTietChiTieuRequest) {
        ChiTietChiTieu chiTietChiTieu = chiTietChiTieuRepository.findById(chiTietChiTieuId)
                .orElseThrow(() -> new AppException(ErrorCode.CHI_TIET_CHI_TIEU_NOT_FOUND));

        chiTietChiTieuMapper.updateChiTietChiTieu(chiTietChiTieu, chiTietChiTieuRequest);
        chiTietChiTieu = chiTietChiTieuRepository.save(chiTietChiTieu);

        return chiTietChiTieuMapper.toChiTietChiTieuResponse(chiTietChiTieu);
    }

    // Xóa chi tiết chi tiêu
    public void deleteChiTietChiTieu(Long chiTietChiTieuId) {
        ChiTietChiTieu chiTietChiTieu = chiTietChiTieuRepository.findById(chiTietChiTieuId)
                .orElseThrow(() -> new AppException(ErrorCode.CHI_TIET_CHI_TIEU_NOT_FOUND));

        chiTietChiTieuRepository.delete(chiTietChiTieu);
    }
    // Tính tổng số tiền khoản chi theo chiTieuId
    public Long getTongSoTienChiTietByChiTieuId(Long chiTieuId) {
        return chiTietChiTieuRepository.findTotalSoTienByChiTieuId(chiTieuId);
    }
}
