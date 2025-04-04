package com.example.Ql_ThuNhap.Service;

import com.example.Ql_ThuNhap.Dto.Request.ChiTieuRequest;
import com.example.Ql_ThuNhap.Dto.Response.ChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalExpenseResponse;
import com.example.Ql_ThuNhap.Dto.Update.ChiTieuUpdateRequest;
import com.example.Ql_ThuNhap.Entity.ChiTieu;
import com.example.Ql_ThuNhap.Entity.User;
import com.example.Ql_ThuNhap.Exception.AppException;
import com.example.Ql_ThuNhap.Exception.ErrorCode;
import com.example.Ql_ThuNhap.Mapper.ChiTieuMapper;
import com.example.Ql_ThuNhap.Repository.ChiTietChiTieuRepository;
import com.example.Ql_ThuNhap.Repository.ChiTieuRepository;
import com.example.Ql_ThuNhap.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChiTieuService {
    @Autowired
    private ChiTieuRepository chiTieuRepository;
    @Autowired
    private ChiTietChiTieuRepository chiTietChiTieuRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChiTieuMapper chiTieuMapper;
    @Autowired
    private SoDuService soDuService;
    @Autowired
    private UserService userService;
    public ChiTieuResponse createChiTieu(ChiTieuRequest chiTieuRequest) {
        User user = userRepository.findById(chiTieuRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        if (!soDuService.kiemTraSoDu(user.getUserId(), chiTieuRequest.getSoTien())) {
            throw new AppException(ErrorCode.SO_DU_KHONG_DU);
        }

        ChiTieu chiTieu = chiTieuMapper.toChiTieu(chiTieuRequest, user);
        chiTieu = chiTieuRepository.save(chiTieu);

        userService.updateSoDu(user.getUserId()); // Cập nhật số dư

        return chiTieuMapper.toChiTieuResponse(chiTieu);
    }



    public List<ChiTieuResponse> getAllChiTieu() {
        return chiTieuRepository.findAll().stream()
                .map(chiTieuMapper::toChiTieuResponse)
                .collect(Collectors.toList());
    }
    public ChiTieuResponse getChiTieu(Long chiTieuId) {
        return chiTieuRepository.findById(chiTieuId)
                .map(chiTieuMapper::toChiTieuResponse)
                .orElseThrow(() -> new RuntimeException("Chi tiêu không tồn tại"));
    }
    public List<ChiTieuResponse> getChiTieuByUserId(Long userId) {
        List<ChiTieu> chiTieuList = chiTieuRepository.findByUser_UserId(userId);
        return chiTieuList.stream()
                .map(chiTieuMapper::toChiTieuResponse)
                .collect(Collectors.toList());
    }
    public TotalExpenseResponse getTotalExpenseByUserId(Long userId) {
        Long totalExpense = chiTieuRepository.getTotalExpenseByUserId(userId);
        return TotalExpenseResponse.builder()
                .userId(userId)
                .totalExpense(totalExpense)
                .build();
    }

    public ChiTieuResponse updateChiTieu(Long chiTieuId, ChiTieuUpdateRequest chiTieuRequest) {
        ChiTieu chiTieu = chiTieuRepository.findById(chiTieuId)
                .orElseThrow(() -> new RuntimeException("Chi tiêu không tồn tại"));

        User user = chiTieu.getUser();

        // Cập nhật thông tin chi tiêu
        chiTieuMapper.updateChiTieu(chiTieu, chiTieuRequest);
        chiTieu = chiTieuRepository.save(chiTieu);

        // Cập nhật số dư
        userService.updateSoDu(user.getUserId());

        return chiTieuMapper.toChiTieuResponse(chiTieu);
    }

    public void deleteChiTieu(Long chiTieuId) {
        ChiTieu chiTieu = chiTieuRepository.findById(chiTieuId)
                .orElseThrow(() -> new RuntimeException("Chi tiêu không tồn tại"));

        User user = chiTieu.getUser();
        chiTieuRepository.delete(chiTieu);
        // Cập nhật số dư
        userService.updateSoDu(user.getUserId());
    }
    // Phương thức tính số dư chi tiêu
    public Long tinhSoDuChiTieu(Long chiTieuId) {
        // Lấy chi tiêu theo chiTieuId
        ChiTieu chiTieu = chiTieuRepository.findById(chiTieuId)
                .orElseThrow(() -> new RuntimeException("Chi tiêu không tồn tại"));

        // Tính tổng số tiền các khoản chi
        Long tongSoTienKhoanChi = chiTietChiTieuRepository.findTotalSoTienByChiTieuId(chiTieuId);

        // Tính số dư chi tiêu
        return chiTieu.getSoTien() - tongSoTienKhoanChi;
    }

}
