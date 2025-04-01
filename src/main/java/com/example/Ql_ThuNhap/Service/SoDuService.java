package com.example.Ql_ThuNhap.Service;

import com.example.Ql_ThuNhap.Repository.SoDuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoDuService {
    @Autowired
    private SoDuRepository soDuRepository;

    public Long getSoDuByUserId(Long userId) {
        return soDuRepository.getSoDuByUserId(userId);
    }

    public boolean kiemTraSoDu(Long userId, Long soTien) {
        Long soDu = getSoDuByUserId(userId);
        return soDu != null && soDu >= soTien;
    }
}
