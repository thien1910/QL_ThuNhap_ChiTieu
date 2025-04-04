package com.example.Ql_ThuNhap.Service;


import com.example.Ql_ThuNhap.Dto.Request.UserCreationRequest;
import com.example.Ql_ThuNhap.Dto.Response.UserResponse;
import com.example.Ql_ThuNhap.Dto.Update.UserUpdateRequest;
import com.example.Ql_ThuNhap.Entity.User;
import com.example.Ql_ThuNhap.Exception.AppException;
import com.example.Ql_ThuNhap.Exception.ErrorCode;
import com.example.Ql_ThuNhap.Mapper.UserMapper;
import com.example.Ql_ThuNhap.Repository.ChiTieuRepository;
import com.example.Ql_ThuNhap.Repository.ThuNhapRepository;
import com.example.Ql_ThuNhap.Repository.TietKiemRepository;
import com.example.Ql_ThuNhap.Repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TietKiemRepository tietKiemRepository;

    @Autowired
    ChiTieuRepository chiTieuRepository;

    @Autowired
    ThuNhapRepository thuNhapRepository;

    public UserResponse createUser(UserCreationRequest request) {
        // Kiểm tra email đã tồn tại hay chưa
        if (userRepository.existsByEmail(request.getEmail()))
                throw new AppException(ErrorCode.Email);
        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getUser(Long userId){
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }
/*
    public UserResponse getMyInfo(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByuserName(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
        return userMapper.toUserResponse(user);
    }
*/

    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }
    public void updateSoDu(Long userId) {
        Long totalIncome = thuNhapRepository.getTotalIncomeByUserId(userId);
        Long totalExpense = chiTieuRepository.getTotalExpenseByUserId(userId);
        Long totalSaving = tietKiemRepository.getTotalSavingByUserId(userId);

        Long soDu = totalIncome - totalExpense - totalSaving;

        if (soDu < 0) {
            throw new RuntimeException("Số dư không thể âm, kiểm tra lại các khoản chi tiêu và tiết kiệm.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        user.setSoDu(soDu);
        userRepository.save(user);
    }
}


