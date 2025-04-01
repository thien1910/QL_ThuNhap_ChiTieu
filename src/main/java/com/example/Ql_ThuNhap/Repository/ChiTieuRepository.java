package com.example.Ql_ThuNhap.Repository;

import com.example.Ql_ThuNhap.Entity.ChiTieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTieuRepository extends JpaRepository<ChiTieu, Long> {
    List<ChiTieu> findByUser_UserId(Long userId);
    @Query("SELECT COALESCE(SUM(c.soTien), 0) FROM ChiTieu c WHERE c.user.userId = :userId")
    Long getTotalExpenseByUserId(@Param("userId") Long userId);

}
