package com.example.Ql_ThuNhap.Repository;

import com.example.Ql_ThuNhap.Entity.SoDu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoDuRepository extends JpaRepository<SoDu, Long> {
    @Query("""
        SELECT (COALESCE((SELECT SUM(t.soTien) FROM ThuNhap t WHERE t.user.userId = :userId), 0) -
                COALESCE((SELECT SUM(c.soTien) FROM ChiTieu c WHERE c.user.userId = :userId), 0) -
                COALESCE((SELECT SUM(s.soTien) FROM TietKiem s WHERE s.user.userId = :userId), 0))
        FROM User u
        WHERE u.userId = :userId
    """)
    Long getSoDuByUserId(@Param("userId") Long userId);
}
