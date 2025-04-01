package com.example.Ql_ThuNhap.Repository;

import com.example.Ql_ThuNhap.Entity.ChiTietChiTieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ChiTietChiTieuRepository extends JpaRepository<ChiTietChiTieu, Long> {

    List<ChiTietChiTieu> findByChiTieu_chiTieuId(Long chiTieuId);
    @Query("SELECT SUM(c.soTienKhoanChi) FROM ChiTietChiTieu c WHERE c.chiTieu.chiTieuId = :chiTieuId")
    Long findTotalSoTienByChiTieuId(Long chiTieuId);
}
