package com.example.Ql_ThuNhap.Service;


import com.example.Ql_ThuNhap.Dto.Request.ThuNhapRequest;
import com.example.Ql_ThuNhap.Dto.Response.ThuNhapResponse;
import com.example.Ql_ThuNhap.Dto.Response.TotalIncomeResponse;
import com.example.Ql_ThuNhap.Dto.Response.UserFinancialSummaryResponse;
import com.example.Ql_ThuNhap.Dto.Update.ThuNhapUpdateRequest;
import com.example.Ql_ThuNhap.Entity.ThuNhap;
import com.example.Ql_ThuNhap.Entity.User;
import com.example.Ql_ThuNhap.Exception.AppException;
import com.example.Ql_ThuNhap.Exception.ErrorCode;
import com.example.Ql_ThuNhap.Mapper.ThuNhapMapper;
import com.example.Ql_ThuNhap.Repository.ChiTieuRepository;
import com.example.Ql_ThuNhap.Repository.ThuNhapRepository;
import com.example.Ql_ThuNhap.Repository.TietKiemRepository;
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
    @Autowired
    private ChiTieuRepository chiTieuRepository;
    @Autowired
   private TietKiemRepository tietKiemRepository;



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

        // Lấy tổng số dư hiện tại của người dùng
        Long soDuHienTai = user.getSoDu();

        // Lấy tổng số tiền chi tiêu của người dùng
        Long totalExpense = chiTieuRepository.getTotalExpenseByUserId(user.getUserId());

        // Lấy tổng số tiền tiết kiệm của người dùng (nếu có)
        Long totalSaving = tietKiemRepository.getTotalSavingByUserId(user.getUserId());

        // Nếu tổng số dư < (chi tiêu + tiết kiệm) thì không được xóa
        if (soDuHienTai < (totalExpense + totalSaving)) {
            throw new AppException(ErrorCode.SO_DU_KO_DU_DE_XOA);
        }

        // Xóa thu nhập
        thuNhapRepository.delete(thuNhap);

        // Cập nhật số dư sau khi xóa thu nhập
        userService.updateSoDu(user.getUserId());
    }


    public TotalIncomeResponse getTotalIncomeByUserId(Long userId) {
        Long totalIncome = thuNhapRepository.getTotalIncomeByUserId(userId);
        return TotalIncomeResponse.builder()
                .userId(userId)
                .totalIncome(totalIncome)
                .build();
    }
    public UserFinancialSummaryResponse getUserFinancialSummary(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        // Lấy số dư hiện tại
        Long soDu = user.getSoDu();

        // Lấy tổng thu nhập của user
        Long totalIncome = thuNhapRepository.getTotalIncomeByUserId(userId);

        // Lấy tổng chi tiêu của user
        Long totalExpense = chiTieuRepository.getTotalExpenseByUserId(userId);

        // Lấy tổng số tiền tiết kiệm của user
        Long totalSaving = tietKiemRepository.getTotalSavingByUserId(userId);
        return UserFinancialSummaryResponse.builder()
                .userId(userId)
                .soDu(soDu)
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .totalSaving(totalSaving)
                .build();
    }
    public UserFinancialSummaryResponse getUserFinancialSummaryByMonthYear(Long userId, int month, int year) {
        // Lấy người dùng từ cơ sở dữ liệu
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        // Lấy số dư hiện tại của người dùng
        Long soDu = user.getSoDu();

        // Lấy tổng thu nhập của người dùng theo tháng và năm
        Long totalIncome = thuNhapRepository.getTotalIncomeByUserIdAndMonthYear(userId, month, year);

        // Lấy tổng chi tiêu của người dùng theo tháng và năm
        Long totalExpense = chiTieuRepository.getTotalExpenseByUserIdAndMonthYear(userId, month, year);

        // Lấy tổng số tiền tiết kiệm của người dùng theo tháng và năm
        Long totalSaving = tietKiemRepository.getTotalSavingByUserIdAndMonthYear(userId, month, year);

        // Trả về thông tin tài chính của người dùng
        return UserFinancialSummaryResponse.builder()
                .userId(userId)
                .soDu(soDu)
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .totalSaving(totalSaving)
                .build();
    }

}
