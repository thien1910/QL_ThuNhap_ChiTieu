package com.example.Ql_ThuNhap.Service;

import com.example.Ql_ThuNhap.Dto.Request.TietKiemRequest;
import com.example.Ql_ThuNhap.Dto.Response.TietKiemResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalSavingResponse;
import com.example.Ql_ThuNhap.Dto.Update.TietKiemUpdateRequest;
import com.example.Ql_ThuNhap.Entity.TietKiem;
import com.example.Ql_ThuNhap.Entity.User;
import com.example.Ql_ThuNhap.Exception.AppException;
import com.example.Ql_ThuNhap.Exception.ErrorCode;
import com.example.Ql_ThuNhap.Mapper.TietKiemMapper;
import com.example.Ql_ThuNhap.Repository.TietKiemRepository;
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
public class TietKiemService {
    @Autowired
    private TietKiemRepository tietKiemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TietKiemMapper tietKiemMapper;
    @Autowired
    private SoDuService soDuService;
    @Autowired
    private UserService userService;
    public TietKiemResponse createTietKiem(TietKiemRequest tietKiemRequest) {
        User user = userRepository.findById(tietKiemRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // Kiểm tra điều kiện ngày
        if (tietKiemRequest.getNgayGuiTietKiem().isAfter(tietKiemRequest.getNgayDatMucTieu()) ||
                tietKiemRequest.getNgayGuiTietKiem().isEqual(tietKiemRequest.getNgayDatMucTieu())) {
            throw new AppException(ErrorCode.NGAY_GUI_PHẢI_TRƯỚC_NGAY_DAT_MUC_TIEU);
        }
        TietKiem tietKiem = tietKiemMapper.toTietKiem(tietKiemRequest, user);
        tietKiem = tietKiemRepository.save(tietKiem);

        userService.updateSoDu(user.getUserId());

        return tietKiemMapper.toTietKiemResponse(tietKiem);
    }
    public List<TietKiemResponse> getAllTietKiem() {
        return tietKiemRepository.findAll().stream()
                .map(tietKiem -> {
                    TietKiemResponse response = tietKiemMapper.toTietKiemResponse(tietKiem);
                    checkAndSetMessage(tietKiem, response);
                    return response;
                })
                .collect(Collectors.toList());
    }

    public TietKiemResponse getTietKiem(Long tietKiemId) {
        return tietKiemRepository.findById(tietKiemId)
                .map(tietKiem -> {
                    TietKiemResponse response = tietKiemMapper.toTietKiemResponse(tietKiem);
                    checkAndSetMessage(tietKiem, response);
                    return response;
                })
                .orElseThrow(() -> new RuntimeException("Tiết kiệm không tồn tại"));
    }

    public List<TietKiemResponse> getTietKiemByUserId(Long userId) {
        return tietKiemRepository.findByUser_UserId(userId).stream()
                .map(tietKiem -> {
                    TietKiemResponse response = tietKiemMapper.toTietKiemResponse(tietKiem);
                    checkAndSetMessage(tietKiem, response);
                    return response;
                })
                .collect(Collectors.toList());
    }
    public TotalSavingResponse getTotalSavingByUserId(Long userId) {
        Long totalSaving = tietKiemRepository.getTotalSavingByUserId(userId);
        return TotalSavingResponse.builder()
                .userId(userId)
                .totalSaving(totalSaving)
                .build();
    }
    public TietKiemResponse updateTietKiem(Long tietKiemId, TietKiemUpdateRequest tietKiemRequest) {
        TietKiem tietKiem = tietKiemRepository.findById(tietKiemId)
                .orElseThrow(() -> new AppException(ErrorCode.TIET_KIEM_NOT_FOUND));

        // Kiểm tra điều kiện ngày
        if (tietKiemRequest.getNgayGuiTietKiem().isAfter(tietKiemRequest.getNgayDatMucTieu()) ||
                tietKiemRequest.getNgayGuiTietKiem().isEqual(tietKiemRequest.getNgayDatMucTieu())) {
            throw new AppException(ErrorCode.NGAY_GUI_PHẢI_TRƯỚC_NGAY_DAT_MUC_TIEU);
        }

        User user = tietKiem.getUser();

        tietKiemMapper.updateTietKiem(tietKiem, tietKiemRequest);
        tietKiem = tietKiemRepository.save(tietKiem);

        // Cập nhật số dư
        userService.updateSoDu(user.getUserId());

        TietKiemResponse response = tietKiemMapper.toTietKiemResponse(tietKiem);
        checkAndSetMessage(tietKiem, response);

        return response;
    }

    public void deleteTietKiem(Long tietKiemId) {
        TietKiem tietKiem = tietKiemRepository.findById(tietKiemId)
                .orElseThrow(() -> new AppException(ErrorCode.TIET_KIEM_NOT_FOUND));

        User user = tietKiem.getUser();

        // Xóa tiết kiệm
        tietKiemRepository.delete(tietKiem);

        // Cập nhật số dư
        userService.updateSoDu(user.getUserId());
    }
    private void checkAndSetMessage(TietKiem tietKiem, TietKiemResponse response) {
        if (tietKiem.getSoTien().equals(tietKiem.getMucTieu())) {
            response.setMessage("🎉 Chúc mừng bạn đã đạt được mục tiêu tiết kiệm! ^.^");
        } else if (tietKiem.getSoTien() > tietKiem.getMucTieu()) {
            response.setMessage("🔥 Bạn đã vượt qua mục tiêu tiết kiệm! Xuất sắc!");
        }
    }

}
