package com.example.Ql_ThuNhap.Service;


import com.example.Ql_ThuNhap.Dto.Request.ThuNhapRequest;
import com.example.Ql_ThuNhap.Dto.Response.ChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Response.ThuNhapResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalIncomeResponse;
import com.example.Ql_ThuNhap.Dto.Update.ThuNhapUpdateRequest;
import com.example.Ql_ThuNhap.Entity.ChiTieu;
import com.example.Ql_ThuNhap.Entity.ThuNhap;
import com.example.Ql_ThuNhap.Entity.User;
import com.example.Ql_ThuNhap.Mapper.ThuNhapMapper;
import com.example.Ql_ThuNhap.Repository.ThuNhapRepository;
import com.example.Ql_ThuNhap.Repository.UserRepository;
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
public class ThuNhapService {
    @Autowired
    private ThuNhapRepository thuNhapRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ThuNhapMapper thuNhapMapper;
    @Autowired
    private UserService userService;


    public ThuNhapResponse createThuNhap(ThuNhapRequest thuNhapRequest) {
        User user = userRepository.findById(thuNhapRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        ThuNhap thuNhap = thuNhapMapper.toThuNhap(thuNhapRequest, user);
        thuNhap = thuNhapRepository.save(thuNhap);

        userService.updateSoDu(user.getUserId()); // Cập nhật số dư

        return thuNhapMapper.toThuNhapResponse(thuNhap);
    }



    public List<ThuNhapResponse> getAllThuNhap() {
        return thuNhapRepository.findAll().stream()
                .map(thuNhapMapper::toThuNhapResponse)
                .collect(Collectors.toList());
    }
    public ThuNhapResponse getThuNhap(Long thuNhapId) {
        return thuNhapRepository.findById(thuNhapId)
                .map(thuNhapMapper::toThuNhapResponse)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
    public List<ThuNhapResponse> getThuNhapByUserId(Long userId) {
        List<ThuNhap> thuNhapList = thuNhapRepository.findByUser_UserId(userId);
        return thuNhapList.stream()
                .map(thuNhapMapper::toThuNhapResponse)
                .collect(Collectors.toList());
    }


    public ThuNhapResponse updateThuNhap(Long thuNhapId, ThuNhapUpdateRequest thuNhapRequest) {
        ThuNhap thuNhap = thuNhapRepository.findById(thuNhapId)
                .orElseThrow(() -> new RuntimeException("Thu nhập không tồn tại"));

        User user = thuNhap.getUser();

        thuNhapMapper.updateThuNhap(thuNhap, thuNhapRequest);
        thuNhap = thuNhapRepository.save(thuNhap);

        // Cập nhật số dư
        userService.updateSoDu(user.getUserId());

        return thuNhapMapper.toThuNhapResponse(thuNhap);
    }

    public void deleteThuNhap(Long thuNhapId) {
        ThuNhap thuNhap = thuNhapRepository.findById(thuNhapId)
                .orElseThrow(() -> new RuntimeException("Thu nhập không tồn tại"));

        User user = thuNhap.getUser();

        // Xóa thu nhập
        thuNhapRepository.delete(thuNhap);

        // Cập nhật số dư
        userService.updateSoDu(user.getUserId());
    }


    public TotalIncomeResponse getTotalIncomeByUserId(Long userId) {
        Long totalIncome = thuNhapRepository.getTotalIncomeByUserId(userId);
        return TotalIncomeResponse.builder()
                .userId(userId)
                .totalIncome(totalIncome)
                .build();
    }



}
