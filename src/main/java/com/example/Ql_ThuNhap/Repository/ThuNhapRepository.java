package com.example.Ql_ThuNhap.Repository;

import com.example.Ql_ThuNhap.Entity.ThuNhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuNhapRepository extends JpaRepository<ThuNhap, Long> {
    List<ThuNhap> findByUser_UserId(Long userId);
    @Query("SELECT COALESCE(SUM(t.soTien), 0) FROM ThuNhap t WHERE t.user.userId = :userId")
    Long getTotalIncomeByUserId(@Param("userId") Long userId);
    @Query("SELECT COALESCE(SUM(t.soTien), 0) FROM ThuNhap t WHERE t.user.userId = :userId AND MONTH(t.ngayNhan) = :month AND YEAR(t.ngayNhan) = :year")
    Long getTotalIncomeByUserIdAndMonthYear(@Param("userId") Long userId, @Param("month") int month, @Param("year") int year);


}
