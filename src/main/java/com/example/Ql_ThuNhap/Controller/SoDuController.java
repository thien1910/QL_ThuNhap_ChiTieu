package com.example.Ql_ThuNhap.Controller;

import com.example.Ql_ThuNhap.Dto.Response.SoDuResponse;
import com.example.Ql_ThuNhap.Service.SoDuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sodu")
public class SoDuController {
    
    @Autowired
    private SoDuService soDuService;
    @GetMapping("/{userId}")
    public SoDuResponse getSoDuByUserId(@PathVariable Long userId) {
        Long soDu = soDuService.getSoDuByUserId(userId);
        return SoDuResponse.builder()
                .userId(userId)
                .soDu(soDu)
                .build();
    }
}
