package com.example.Ql_ThuNhap.Repository;

import com.example.Ql_ThuNhap.Entity.TietKiem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TietKiemRepository extends JpaRepository<TietKiem, Long> {
    List<TietKiem> findByUser_UserId(Long userId);

    @Query("SELECT COALESCE(SUM(t.soTien), 0) FROM TietKiem t WHERE t.user.userId = :userId")
    Long getTotalSavingByUserId(@Param("userId") Long userId);
}
